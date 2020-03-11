package com.opal.brew.production.services;

import com.opal.brew.production.models.InProgressProduct;
import com.opal.brew.production.models.Recipe;
import com.opal.brew.stock.models.CurrentMaterialStock;
import com.opal.brew.stock.repositories.CurrentMaterialStockRepository;
import com.opal.brew.production.repositories.InProgressRepository;
import com.opal.brew.production.repositories.RecipeRepository;
import java.util.List;
import javax.ws.rs.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InProgressServiceImpl implements InProgressService {

  private InProgressRepository progRepo;
  private CurrentMaterialStockRepository stockRepo;
  private RecipeRepository recipeRepository;

  public InProgressServiceImpl() {
  }

  @Autowired
  public InProgressServiceImpl(InProgressRepository progRepo,
      CurrentMaterialStockRepository stockRepo,
      RecipeRepository recipeRepository) {
    this.progRepo = progRepo;
    this.stockRepo = stockRepo;
    this.recipeRepository = recipeRepository;
  }

  @Override
  public void save(InProgressProduct inProgressProduct) {
    if (checkQuntities(inProgressProduct)){
    progRepo.save(inProgressProduct);
    stockRepo.reserveMaterial("hop", inProgressProduct.getReservedHop());
    stockRepo.reserveMaterial("malt", inProgressProduct.getReservedMalt());
    stockRepo.reserveMaterial("yeast", inProgressProduct.getReservedYeast());
    } else {
      throw new BadRequestException("Not enough materials to begin production");
    }
  }

  @Override
  public InProgressProduct findById(int id) {
    return progRepo.findById(id).orElse(null);
  }

  @Override
  public void approve(InProgressProduct inProgressProduct) {
    inProgressProduct.setApproved(true);
    stockRepo.acceptMaterial("hop", inProgressProduct.getReservedHop());
    stockRepo.acceptMaterial("malt", inProgressProduct.getReservedMalt());
    stockRepo.acceptMaterial("yeast", inProgressProduct.getReservedYeast());
    progRepo.save(inProgressProduct);
  }

  @Override
  public void cancel(InProgressProduct inProgressProduct) {
    stockRepo.freeUpReserved("hop", inProgressProduct.getReservedHop());
    stockRepo.freeUpReserved("malt", inProgressProduct.getReservedMalt());
    stockRepo.freeUpReserved("yeast", inProgressProduct.getReservedYeast());
    progRepo.deleteById(inProgressProduct.getId());
  }

  @Override
  public boolean isValid(InProgressProduct inProgressProduct) {
    return inProgressProduct.getRecipe() != null && inProgressProduct.getStartingDate() != null;
  }

  @Override
  public InProgressProduct create(String recipeName) {
    Recipe recipe = recipeRepository.findByRecipeName(recipeName);
    if (recipe != null) {
      InProgressProduct productToSave = new InProgressProduct();
      recipe.addProgressProducts(productToSave);
      return productToSave;
    } else {
      throw new BadRequestException("No recipe with that name!");
    }
  }

  @Override
  public List<InProgressProduct> findAllInProgress() {
    return progRepo.findAllByIsApproved(false);
  }

  public boolean checkQuntities(InProgressProduct product){
    int neededHop = product.getReservedHop();
    int neededYeast = product.getReservedYeast();
    int neededMalt = product.getReservedMalt();
    int currentHop = 0;
    int currentYeast = 0;
    int currentMalt = 0;
    List <CurrentMaterialStock> currentAmounts = stockRepo.findAll();
    for (CurrentMaterialStock stock: currentAmounts) {
      if (stock.getMaterial().toLowerCase().equals("hop")){
        currentHop = stock.getQuantity();
      } else if (stock.getMaterial().toLowerCase().equals("yeast")){
        currentYeast = stock.getQuantity();
      } else if (stock.getMaterial().toLowerCase().equals("malt")) {
        currentMalt = stock.getQuantity();
      } else {
        throw new BadRequestException("Unknown material requested");
      }
    }
    return neededHop <= currentHop && neededMalt <= currentMalt && neededYeast <= currentYeast;
  }
}

package com.opal.brew.setup;

import com.opal.brew.production.models.InProgressProduct;
import com.opal.brew.production.services.RecipeService;
import com.opal.brew.stock.services.RawMaterialService;
import com.opal.brew.users.MyUser;
import com.opal.brew.production.models.Recipe;
import com.opal.brew.stock.models.dtos.RawDto;
import com.opal.brew.production.services.InProgressService;
import com.opal.brew.users.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

  @Autowired
  MyUserService myUserService;
  @Autowired
  RawMaterialService rawMaterialService;
  @Autowired
  InProgressService inProgressService;
  @Autowired
  RecipeService recipeService;

  public void initialize() throws Exception {
    //recipeFactory.build();

    //Setup users
    MyUser admin = new MyUser("admin", "admin@admin.com", "admin", "ADMIN", true);
    myUserService.save(admin);

    MyUser worker = new MyUser("worker", "worker@worker.com", "worker", "WORKER", true);
    myUserService.save(worker);

    //Setup materials
    RawDto baseHop = new RawDto("hop", "basic",50000,"A202002201705");
    RawDto baseMalt = new RawDto("malt","basic",50000,"A202002201707");
    RawDto baseYeast = new RawDto("yeast","basic",50000,"A202002201709");
    rawMaterialService.save(baseHop);
    rawMaterialService.save(baseMalt);
    rawMaterialService.save(baseYeast);

    //Setup recipes
    InProgressProduct someProduct = new InProgressProduct();
    InProgressProduct otherProduct = new InProgressProduct();
    InProgressProduct thirdProduct = new InProgressProduct();
    InProgressProduct fourthProduct = new InProgressProduct();

    Recipe soproni = new Recipe("soproni", 150,100,20);
    soproni.addProgressProducts(someProduct);
    soproni.addProgressProducts(otherProduct);

    Recipe dreher = new Recipe("dreher",120,120,18);
    dreher.addProgressProducts(thirdProduct);

    Recipe heineken = new Recipe("heineken",145,135,25);
    dreher.addProgressProducts(fourthProduct);

    recipeService.save(soproni);
    recipeService.save(dreher);
    recipeService.save(heineken);

    inProgressService.save(someProduct);
    inProgressService.save(otherProduct);
    inProgressService.save(thirdProduct);
    inProgressService.save(fourthProduct);

  }
}

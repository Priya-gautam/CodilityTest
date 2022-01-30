ShopPage
==================================
h2_listOfItems				css					article li[class*='product'] h2[class*='title']
h1_shopPageTitle				css					h1[class="page-title"]
bdi_itemPrice				xpath 				//article//li[contains(@class,'product')]//h2[contains(@class,'title')][text()='${name}']/../parent::li//span[@class="price"]//ins//bdi
div_wishListButton			xpath				//article//li[contains(@class,'product')]//h2[contains(@class,'title')][text()='${name}']/../parent::li//div[contains(@class,'add-button')]
span_addedToWishListIcon		xpath				//article//li[contains(@class,'product')]//h2[contains(@class,'title')][text()='${name}']/../parent::li//span[@class="feedback"]
==================================
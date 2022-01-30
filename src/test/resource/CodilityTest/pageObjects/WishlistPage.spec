WishlistPage
==================================
h2_wishlistTitle				css					div[class="wishlist-title-container"] h2
bdi_productAndPrice			xpath				//tbody[@class="wishlist-items-wrapper"]//tr//td[@class="product-name"]//a[contains(text(),'$')]/../parent::tr//td[@class='product-price']//ins//bdi[contains(.,'%')]
a_addToCardIcon				xpath				//tr//td[@class="product-name"]//a[contains(text(),'${name}')]/../parent::tr//td[@class="product-add-to-cart"]//a
==================================
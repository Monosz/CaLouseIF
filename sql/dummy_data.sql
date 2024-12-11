
INSERT INTO users(user_name, user_password, user_phone, user_address, user_role)
VALUES
	("Dummy Buyer", "@buyer123", "+62987654321", "124 Conch Street", "Buyer"),
	("Dummy Seller", "@seller123", "+62123456789", "123 Sesame Street", "Seller"),
	("buyer", "buyer", "+621234567890", "Jl. Jalanan Buyer", "Buyer"),
	("buyer2", "buyer2", "+62123456790", "Jl. Jalanan Buyer II", "Buyer"),
	("seller", "seller", "+621234567890", "Jl. Jalanan Seller", "Seller");
	
INSERT INTO items(item_name, item_size, item_price, item_category, item_status, item_wishlist, item_offer_status, item_offerer_user_id)
VALUES
	("H&M Mouse", "L", 150000, "Jacket", "ACCEPTED", 3, 120000, 3),
	("UNIQLO Mickey", "L", 150000, "T-Shirt", "ACCEPTED", 1, NULL, NULL),
	("NEW STATE PARALLEL UI", "L", 150000, "Jacket", "ACCEPTED", 0, 120000, 3),
	("UNIQLO HXH", "L", 200000, "Shirt", "PURCHASED", 0, NULL, NULL),
	("WOPPER", "XL", 250000, "Shirt", "PURCHASED", 0, NULL, NULL),
	("BIG HONEY", "XL", 360000, "Shirt", "PURCHASED", 0, NULL, NULL),
	("POLO Snoopy", "M", 150000, "Dress", "PENDING", 0, NULL, NULL),
	("POLO Reject", "M", 150000, "Dress", "PENDING", 0, NULL, NULL);

	
INSERT INTO wishlists(user_id, item_id)
VALUES
	(3, 1),
	(3, 2),
	(1, 1),
	(4, 1);


INSERT INTO transactions(user_id, item_id)
VALUES
	(1, 4),
	(3, 5),
	(3, 6);
	

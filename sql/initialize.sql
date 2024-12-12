CREATE DATABASE calouseif;
USE calouseif;

CREATE TABLE users (
	user_id INT PRIMARY KEY AUTO_INCREMENT,
	user_name VARCHAR(50) UNIQUE NOT NULL,
	user_password VARCHAR(50) NOT NULL,
	user_phone VARCHAR(15) NOT NULL,
	user_address VARCHAR(255) NOT NULL,
	user_role VARCHAR(10) NOT NULL
);

CREATE TABLE items (
	item_id INT PRIMARY KEY	AUTO_INCREMENT,
	item_name VARCHAR(255) NOT NULL,
	item_size VARCHAR(255) 	NOT NULL,
	item_price INT NOT NULL,
	item_category VARCHAR(255) NOT NULL,
	item_status VARCHAR(10) NOT NULL,
	item_wishlist INT NOT NULL,
	item_offer_status INT,
	
	item_offerer_id INT,
	item_seller_id INT NOT NULL,

	FOREIGN KEY (item_offerer_id) REFERENCES users(user_id),
	FOREIGN KEY (item_seller_id) REFERENCES users(user_id)
);
	
CREATE TABLE wishlists (
    wishlist_id	INT PRIMARY KEY	AUTO_INCREMENT,
    user_id INT NOT NULL,
    item_id INT NOT NULL,
    
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (item_id) REFERENCES items(item_id)
);
	
CREATE TABLE transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    item_id INT NOT NULL,
    
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (item_id) REFERENCES items(item_id)
);
	

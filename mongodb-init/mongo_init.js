db = db.getSiblingDB('very_good_purchase_db');

db.createUser(
            {
                user: "PurchaseUser",
                pwd: "Test1234",
                roles: [
                    {
                        role: "readWrite",
                        db: "very_good_purchase_db"
                    }
                ]
            }
);

db.createCollection('purchase');

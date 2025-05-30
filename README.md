# 🥪 Albino-licious Sandwich Shop CLI Application


A menu-driven command-line Java application that simulates a fully functional deli sandwich shop. Users can build custom sandwiches, select from signature recipes, add drinks and chips, apply coupons, add a predetermined or custom tip, and print detailed receipts. Each order is saved as a time-stamped receipt file inside a dedicated receipts/ folder.

---

## 🍴 Features

- 🥪 Custom Sandwich Builder
Interactive topping selection by category: meats, cheeses, veggies, sauces, and premium options.

- ⭐ Signature Sandwiches
Prebuilt recipes like the BLT and Philly Cheese Steak, auto-filled with toppings but still customizable.

- 🥤 Add Drinks
Choose size and flavor from a list of popular options.

-🍟 Add Chips
Select from several flavors to complete your meal.

- 🧾 Apply Coupons
Support for percent-based discount codes (e.g., SAVE10 for 10% off).

- 💸 Checkout & Receipt Generation
Final receipt displays all items, toppings, prices with two-decimal formatting, and customer name.
Receipt is saved as a text file with a time-stamped filename (e.g., 20240527-183422.txt).

- 📂 Receipt Folder Management
Automatically creates a receipts/ directory (if not already present) for clean organization. 
---
### Application Screens

![Screenshot 2025-05-30 011359](https://github.com/user-attachments/assets/f12085cf-581b-4904-a88c-efba084b91d7)

Sandwich Menu

![Screenshot 2025-05-30 011718](https://github.com/user-attachments/assets/9973db96-5044-40ab-ae31-5e91bcb20a4b)

Signature Selection

![Screenshot 2025-05-30 011851](https://github.com/user-attachments/assets/64a4c322-d2cc-4741-b2e8-addc13a0114b)

![Screenshot 2025-05-30 012025](https://github.com/user-attachments/assets/bfa77746-8680-4bca-8f40-e9daef69cdb0)

Custom Sandwich

![Screenshot 2025-05-30 012132](https://github.com/user-attachments/assets/d06807bd-498d-4441-9fa1-99783dd2f1c0)

![Screenshot 2025-05-30 012234](https://github.com/user-attachments/assets/312f2e4e-7621-45b0-bd6f-16816d877d60)


Drink + Chips

![Screenshot 2025-05-30 012413](https://github.com/user-attachments/assets/5a899699-2072-4952-b085-1eac12758caf)

Coupon

![Screenshot 2025-05-30 012621](https://github.com/user-attachments/assets/ef9f33a4-b707-4266-807d-290a965911af)

TIP 

![Screenshot 2025-05-30 012810](https://github.com/user-attachments/assets/da19f79c-e630-4cdc-a034-0dd986109ec8)


receipts

---
####🧠 Code I Learned the Most From
![Screenshot 2025-05-30 013929](https://github.com/user-attachments/assets/94fd891e-a128-4396-a8db-8785a79b322a)

This snippet taught me the power of using a HashSet to manage state and uniqueness efficiently in a program. The usedCoupons set tracks which coupon codes have already been applied, ensuring a coupon can’t be reused in the same session. The validCoupons set acts like a whitelist of allowed codes. Both sets gave me valuable insight into when and why you’d use a Set over something like a List.
A HashSet was perfect here because it prevents duplicates automatically and lets me write intuitive logic. Before this, I thought of collections mainly as lists of things. This example showed me that choosing the right collection type (like a Set for uniqueness) makes logic safer, simpler, and more efficient. It also taught me how to use Set.of(...) to create immutable sets for constants — a clean, modern Java feature that avoids bugs.
---
# 📊🥪FlowChart & File Structure 

![Screenshot 2025-05-30 023055](https://github.com/user-attachments/assets/3b569e9f-2eba-47a1-a0ad-b0595979fe5e)


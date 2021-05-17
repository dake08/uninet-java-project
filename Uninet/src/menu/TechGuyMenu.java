package menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import acters.User;
import acters.TechSupportGuy;
import resources.Order;
import resources.Wsp;

public class TechGuyMenu implements Menu {
	private TechSupportGuy techGuy;

	/**
	 * @param user
	 */
	public TechGuyMenu(User user) {
		this.techGuy = (TechSupportGuy) user;
	}

	public void acceptOrders(int orderNumber) {
		int i = 1;
		for (Order order : Wsp.getInstance().ITOrders) {
			if (!order.getStatus()) {
				if (i == orderNumber) {
					order.setOrderAccepted();
					System.out.println("Order: " + order.toString() + "is accepted");
				}
				i++;
			}
		}
	}

	public void deleteOrders(int orderNumber) {
		int i = 1;
		for (Order order : Wsp.getInstance().ITOrders) {
			if (!order.getStatus()) {
				if (i == orderNumber) {
					techGuy.rejectOrder(order);
					System.out.println("Order: " + order.toString() + "is rejected");
				}
				i++;
			}
		}
	}

	public void startMenu() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			menu: while (true) {
				System.out.println("Which option do you choose?");
				System.out.println("\n 1)View new orders \n 2.View done orders \n 3.Exit");
				int choice = Integer.parseInt(reader.readLine());
				if (choice == 1) {
					viewNewOrders: while (true) {
						System.out.println(techGuy.viewNewOrders());
						System.out.println("\n 1)Accept order \n 2.Reject orders \n 3.Return back \n 4.Exit");
						choice = Integer.parseInt(reader.readLine());
						if (choice == 1) {
							System.out.println("Choose order to accept/done");
							int acceptNumber = Integer.parseInt(reader.readLine());
							this.acceptOrders(acceptNumber);
							System.out.println("\n 1) Return back \n 2) Exit");
							choice = Integer.parseInt(reader.readLine());
							if(choice == 1) continue menu;
							if(choice == 2) {System.out.println("Goodbye!!! Saving data..."); Wsp.getInstance().save(); break menu;}
							break;
						}
						if (choice == 2) {
							System.out.println("Choose order to reject/delete");
							int deleteNumber = Integer.parseInt(reader.readLine());
							if(Wsp.getInstance().getITorders().size()>0)
								this.deleteOrders(deleteNumber);
							System.out.println("\n 1) Return back \n 2) Exit");
							choice = Integer.parseInt(reader.readLine());
							if(choice == 1) continue menu;
							if(choice == 2) {System.out.println("Goodbye!!! Saving data..."); Wsp.getInstance().save(); break menu;}
							break;
						}
						if (choice == 3)
							continue menu;
						if (choice == 4) {
							System.out.println("Goodbye!!! Saving data...");
							Wsp.getInstance().save();
							break menu;
						}
						break viewNewOrders;
					}
				}
				if (choice == 2) {
					viewDoneOrders: while (true) {
						techGuy.viewDoneOrders();
						System.out.println("\n 1.Return back \n 2.Exit");
						choice = Integer.parseInt(reader.readLine());
						if (choice == 1) {
							continue menu;
						}
						if (choice == 2) {
							System.out.println("Goodbye!!! Saving data...");
							Wsp.getInstance().save();
							break menu;
						}
						;
						break viewDoneOrders;
					}
				}
				if (choice == 3) {
					System.out.println("Goodbye!!! Saving data...");
					Wsp.getInstance().save();
					break menu;
				}
			}
		} catch (Exception e) {
			System.out.println("Oh, something wrong happened..");
			e.printStackTrace();
			Wsp.getInstance().save();
		}
	}
}

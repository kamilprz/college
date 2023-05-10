import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClubManager {

	private static String SEPARATOR = ",";
	private static int NAME_INDEX = 0;
	private static int ADDRESS_INDEX = 1;
	private static int YEAR_INDEX = 2;
	private static int EMAIL_INDEX = 3;
	private static int groupId = 0;
	private static Scanner reader;

	public static void main(String[] args) {
		ClubManager club = new ClubManager();
		ArrayList<ClubMember> clubMembers = new ArrayList<ClubMember>();
		ArrayList<Group> clubGroups = new ArrayList<Group>();

		try (BufferedReader br = new BufferedReader(new FileReader("members.txt"))) {
			boolean eof = false;
			while (!eof) {
				String member = br.readLine();
				if (member != null) {
					String[] memberParameters = member.split(SEPARATOR);
					String name = memberParameters[NAME_INDEX];
					String address = memberParameters[ADDRESS_INDEX];
					int year = Integer.parseInt(memberParameters[YEAR_INDEX].trim());
					String email = memberParameters[EMAIL_INDEX];
					clubMembers.add(new ClubMember(name, address, year, email));
				} else {
					eof = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		club.printMembers(clubMembers);

		/*
		 * System.out.println(); clubMembers.add(new ClubMember("Gene", "Dublin 16",
		 * "yuoung@gene.com")); club.printMembers(clubMembers);
		 */

		boolean toContinue = true;
		reader = new Scanner(System.in);
		while (toContinue) {
			int function = getRequest();
			switch (function) {
			case 0:
				toContinue = false;
				break;
			case 1:
				clubGroups.add(club.createGroupUI());
				break;
			case 2:
				System.out.println("Club members are:");
				club.printMembers(clubMembers);
				break;
			case 3:
				club.viewDetails(clubMembers);
				break;
			case 4:
				club.changeAddressEmail(clubMembers);
				break;
			case 5:
				club.addMemberToGroupUI(clubMembers,clubGroups);
				break;
			case 6:
				club.printGroupNamesAndNumberOfMembers(clubGroups);
				break;
			case 7:
				for(Group group : clubGroups) {
					club.printGroupMembers(group);
				}
			}
		}

	}

	public void printMembers(ArrayList<ClubMember> clubMembers) {
		for (int i = 0; i < clubMembers.size(); i++) {
			System.out.print(i + 1 + ") ");
			System.out.println(clubMembers.get(i).getName());
		}
	}

	// returns null if no member was found
	public ClubMember getMember(ArrayList<ClubMember> clubMembersList, String name) {
		for (int i = 0; i < clubMembersList.size(); i++) {
			if (clubMembersList.get(i).getName().equals(name)) {
				return clubMembersList.get(i);
			}
		}
		return null;
	}
	
	public Group getGroup(ArrayList<Group> groups, String groupDescription) {
		for(int i = 0 ; i < groups.size(); i++) {
			if(groups.get(i).getGroupDescription().equals(groupDescription)) {
				return groups.get(i);
			}
		}
		return null;
	}

	// updates the email or address if the value passed is not a null
	public void updateMember(ClubMember member, String address, String email) {
		if (member != null) {
			if (email != null) {
				member.setEmail(email);
			}
			if (address != null) {
				member.setAddress(address);
			}
		}
	}

	public Group createGroup(int groupId, String groupDescription) {
		return new Group(groupId, groupDescription, new ArrayList<ClubMember>());
	}

	public Group createGroup(int groupId, String groupDescription, ArrayList<ClubMember> groupMembers) {
		return new Group(groupId, groupDescription, groupMembers);
	}

	public void addMemberToGroup(Group group, ClubMember member) {
		if (group != null && member != null) {
			ArrayList<ClubMember> groupMembers = group.getGroupMembers();
			if (!groupMembers.contains(member)) {
				groupMembers.add(member);
			}
		}
	}

	public void printGroupMembers(Group group) {
		if (group != null && group.getGroupMembers().size() > 0) {
			System.out.printf("Members of group %s", group.getGroupDescription());
			printMembers(group.getGroupMembers());
		}
	}

	public void printGroupNamesAndNumberOfMembers(ArrayList<Group> clubGroups) {
		if (clubGroups != null && clubGroups.size() > 0) {
			System.out.printf("Group Name       Number of Members%n");
			for (int i = 0; i < clubGroups.size(); i++) {
				Group group = clubGroups.get(i);
				System.out.printf("%s           %s%n", group.getGroupDescription(), group.getGroupMembers().size());
			}
		}

	}

	private Group createGroupUI() {
		System.out.println("Enter a group description");
		String description = reader.nextLine();
		return createGroup(groupId++, description);

	}

	private void viewDetails(ArrayList<ClubMember> clubMembers) {
		System.out.println();
		boolean toAsk = true;
		while (toAsk) {
			
			System.out.println("What is the name of a member you want to see?(or 0 to get back to menu)");
			String name = reader.nextLine();
			ClubMember member = getMember(clubMembers, name);
			if(member == null) {
				System.out.println("Such member does not exist!");
			}
			if(member != null) {
				System.out.println(member);
				toAsk = false;
			} else if(name.equals("0")) {
				toAsk = false;
			}
		}
	}
	
	private void changeAddressEmail(ArrayList<ClubMember> clubMembers) {
		System.out.println();
		boolean toAsk = true;
		while (toAsk) {
			
			System.out.println("What is the name of a member you want to alter?(or 0 to get back to menu)");
			String name = reader.nextLine();
			ClubMember member = getMember(clubMembers, name);
			if(member == null) {
				System.out.println("Such member does not exist!");
			}
			if(member != null) {
				System.out.println("Enter new address(or space if you don't want to change it)>");
				String newAddress = reader.nextLine();
				if(newAddress.equals("")) newAddress = null;
				System.out.println("Enter new email(or space if you don't want to change it)>");
				String newEmail = reader.nextLine();
				if(newEmail.equals("")) newEmail = null;
				updateMember(member, newAddress, newEmail);
				toAsk = false;
			} else if(name.equals("0")) {
				toAsk = false;
			}
		}
	}
	
	private void addMemberToGroupUI(ArrayList<ClubMember> clubMembers, ArrayList<Group> clubGroups) {
		System.out.println();
		boolean toAsk = true;
		while (toAsk) {
			
			System.out.println("What is the name of a member you want to add to group?(or 0 to get back to menu)");
			String name = reader.nextLine();
			System.out.println("What is the name of a group?");
			String groupName = reader.nextLine();
			Group group = getGroup(clubGroups, groupName);
			ClubMember member = getMember(clubMembers, name);
			
			if(member != null && group != null) {
				addMemberToGroup(group, member);
				toAsk = false;
			} else if(name.equals("0")) {
				toAsk = false;
			} 
			if(group == null) {
				System.out.println("Such group does not exist!");
			}
			if(member == null) {
				System.out.println("Such member does not exist!");
			}
		}
	}

	private static int getRequest() {
		boolean toAsk = true;
		int choice = -1;
		while (toAsk) {
			try {
				System.out.println();
				System.out.println("Choose what you want to do and type the number:");
				System.out.println("0 - Stop the program");
				System.out.println("1 - Create new group");
				System.out.println("2 - View club members by name");
				System.out.println("3 - View the details of an individual members");
				System.out.println("4 - Change the address or email address of a member");
				System.out.println("5 - Add members to groups");
				System.out.println("6 - View groups and the number of members");
				System.out.println("7 - View the names of the members in a group");
				choice = reader.nextInt();
				if (choice < 0 || choice > 7) {
					throw new java.util.InputMismatchException();
				}
				toAsk = false;
				reader.nextLine();
			} catch (java.util.InputMismatchException e) {
				System.out.println("Please enter a correct number!");
				// reader.nextLine();
			}
		}
		return choice;
	}

}
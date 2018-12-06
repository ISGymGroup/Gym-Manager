package com.company;
import java.util.*;

public class Main {
    public static void main(String args[])
    {
    m_Menu();





    }

    public static void m_Menu()
    {
        System.out.println("Welcome to Gym Manger");
        Member[] memberList = new Member[1];
        int memCreated = 0;
        boolean in_Use = true;
        int uChoice = -1;
        int[][] lockers = fillLockers();
        while(in_Use)
        {
         greetOpt();
         uChoice = verifyUI();
         switch (uChoice) {
             case 1:
                 memberList = newMember(memberList,memCreated);
                 memCreated++;
                 System.out.println();
                 break;
             case 2:
                 memberCheckIn(memCreated,memberList);
                 System.out.println();
                 break;
             case 3:
                 memberCheckOut(memCreated,memberList);
                 System.out.println();
                 break;
             case 4:
                 lockers = reserveLocker(lockers);
                 System.out.println();
                break;
             case 5:
                 peopleIn(memCreated,memberList);
                 System.out.println();
                 break;
             case 0:
                 in_Use = false;
                 if(memCreated > 0)
             {
                 for (Member o : memberList)
                     o.check_Out();
             }
                 break;



         }

        }
        System.out.println("Now Closed");


    }

    public static Member[] newMember(Member[] memList, int x)
    {
        if(x == 0)
        {
            Member[] nMemList = new Member[1];
            nMemList[0] = crtNewMem();
            return nMemList;
        }
        else
            {
            Member[] nMemList = new Member[memList.length + 1];
            for (int y = 0; y < memList.length; y++) {
                nMemList[y] = memList[y];
            }

            nMemList[nMemList.length - 1] = crtNewMem(memList);
                return nMemList;
        }
    }

    public static Member crtNewMem()
    {
        Member tempMem = new Member();
        Scanner userIn = new Scanner(System.in);
        String tempName;
        int memID;
        System.out.println("Please enter your full name.");
        tempName = userIn.nextLine();
        tempName = verifyN(tempName);
        memID = genID();
        tempMem.set_Name(tempName);
        tempMem.set_ID(memID);
        System.out.printf("Your user id is %d", tempMem.get_ID());
        System.out.println();
        return tempMem;


    }

    public static Member crtNewMem(Member[] memList)
    {
        Member tempMem = new Member();
        Scanner userIn = new Scanner(System.in);
        int memID;
        String tempName;
        System.out.println("Please enter your full name.");
        tempName = userIn.nextLine();
        tempName = verifyN(tempName);
        memID = genID(memList);
        tempMem.set_Name(tempName);
        tempMem.set_ID(memID);
        System.out.printf("Your user id is %d", tempMem.get_ID());
        System.out.println();
        return tempMem;

    }

    public static int genID()
    {
        Random ranID = new Random();
        return (ranID.nextInt(99999)+1);
    }

    public static int genID(Member[] memList)
    {
        Random ranID = new Random();
        int ranN;
        ranN = ranID.nextInt(99999)+ 1;
        for (int x = 0; x < memList.length-1; x++ )
        {
            if(ranN == memList[x].get_ID())
            {
                return genID(memList);
            }
        }
        return ranN;

    }

    public static String verifyN(String tName)
    {
        Scanner userIn = new Scanner(System.in);
        int x;
        System.out.println(tName);
        System.out.println("Is this name correct? If it is enter 1, if not enter any other number.");
        x = userIn.nextInt();
        if(x == 1)
            return tName;
        else
        {
            System.out.println("Please enter your full name.");
            userIn.nextLine();
            tName = userIn.nextLine();
            return verifyN(tName);
        }
    }


    public static int verifyUI()
    {
        Scanner userIn = new Scanner(System.in);
        int uC = userIn.nextInt();
        if(uC > -1 && uC <6)
        {
            return uC;
        }
        else
        {
            System.out.println("Sorry that is not an available option.");
            greetOpt();
            return verifyUI();
        }
    }

    public static void greetOpt()
    {
        System.out.println("------------------------");
        System.out.println("Here are your options: ");
        System.out.println("Enter 1 to register a new member.");
        System.out.println("Enter 2 to check in a member.");
        System.out.println("Enter 3 to check out a member.");
        System.out.println("Enter 4 to reserve a locker.");
        System.out.println("Enter 5 to see how many people are checked in.");
        System.out.println("Enter 0 to close for the day. ");
    }

    public static void memberCheckIn(int x, Member[] memList)
    {
        if (x == 0)
        {
            System.out.println("Sorry there is no one to check in. Please create a member before checking in.");
        }
        else {
            Scanner userIn = new Scanner(System.in);
            System.out.println("Please enter your User ID");
            int uID = userIn.nextInt();
            int whom = -1;
            boolean uThere = false;
            boolean ncheckIn = false;
            for(int y = 0; y < memList.length; y++)
            {
                if(memList[y].get_ID() == uID)
                {
                    uThere = true;
                    whom = y;
                    if(!memList[y].getAttendence())
                    {
                        memList[y].checkIn();
                        ncheckIn = true;
                    }
                }
            }
            if(uThere && ncheckIn)
            {
                System.out.println(memList[whom].get_Name() + " , You are now Checked in");
            }
            else if(uThere)
            {
                System.out.println(memList[whom].get_Name() + " , You are already checked in.");
            }
            else
            {
                System.out.println("You may have put in the wrong user ID please try again.");
            }

        }
    }
    public static void memberCheckOut(int x, Member[] memList)
    {
        if (x == 0)
        {
            System.out.println("Sorry there is no one to check out. Please create a member before checking in.");
        }
        else {
            Scanner userIn = new Scanner(System.in);
            System.out.println("Please enter your User ID");
            int uID = userIn.nextInt();
            int whom = -1;
            boolean uThere = false;
            boolean checkedIn = false;
            for(int y = 0; y < memList.length; y++)
            {
                if(memList[y].get_ID() == uID)
                {
                    uThere = true;
                    whom = y;
                    if(memList[y].getAttendence()) {
                        memList[y].check_Out();
                        checkedIn = true;
                    }

                }
            }
            if(uThere && checkedIn)
            {
                System.out.println(memList[whom].get_Name() + " , You are now Checked out");
            }
            else if(uThere)
            {
                System.out.println(memList[whom].get_Name() + " , You are not checked in. Please Check in before checking out.");
            }
            else
            {
                System.out.println("You may have put in the wrong user ID please try again.");
            }

        }
    }

    public static int[][] fillLockers()
    {
        int counter = 1;
        int[][] lockers = new int[5][5];
        for(int x = 0; x < lockers[0].length; x++)
        {
            for(int y = 0; y < lockers.length; y++) {
                lockers[x][y] = counter;
                counter++;
            }
        }
        return lockers;
    }

    public static void displayLockers(int[][] lockers)
    {
        for(int x = 0; x < lockers[0].length; x++)
        {
            for(int y = 0; y < lockers.length; y++) {
                System.out.print(lockers[x][y] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] reserveLocker(int[][] lockers)
    {

        System.out.println("Please enter the locker number you would like to select. Any Lockers displayed with a 0 are taken");
        displayLockers(lockers);
        Scanner userIn = new Scanner(System.in);
        int x = userIn.nextInt();
        boolean available = false;
        if(x > 0 && x < 26)
        {
            for(int z = 0; z < lockers[0].length; z++)
            {
                for(int y = 0; y < lockers.length; y++) {
                    if(x == lockers[z][y])
                    {
                        lockers[z][y] = 0;
                        available = true;
                    }

                }
            }
            if(available){
                System.out.println("Locker # " + x + " is yours.");
            }
            else{
                System.out.println("Sorry that locker is Taken. Please try another");
            }
        }
        else
        {
            System.out.println("Sorry there is no such locker number. Please look at the list and try again.");
        }

        return lockers;

    }
    public static void peopleIn(int mems,Member memList[])
    {
        int checked_In = 0;
        if(mems == 0)
        {
            System.out.println("We have no member to check in.");
        }
        else
        {
            for(int x = 0; x < memList.length; x++)
            {
                if(memList[x].getAttendence())
                checked_In++;
            }
            System.out.print("There are currently " + checked_In + " people checked in.");
        }

    }
}

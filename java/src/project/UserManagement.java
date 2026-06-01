package project;

import java.util.*;

public class UserManagement {
    public static void main(String[] arg) {

        List<User> users = new ArrayList<>();
        users.add(new User("Sumit", true, new HashSet<>(Arrays.asList("ADMIN", "USER"))));
        users.add(new User("Rahul", false, new HashSet<>(Arrays.asList("USER"))));
        users.add(new User("Aditya", true, new HashSet<>(Arrays.asList("MANAGER"))));


//        Remove INactive User
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            if (!iterator.next().isActive()){
//                iterator.remove();
                   continue;
            }
        }

        System.out.println("*****   Active User   *****");
        System.out.println();
        for(User user:users)
            System.out.println("\t"+user.getName());
    }
}

package vehiclerental;

import java.time.LocalTime;
import java.util.*;

public class RentService {

    private Set<Rentable> rentables = new HashSet<>();
    private Set<User> users = new HashSet<>();
    private Map<Rentable, User> actualRenting = new TreeMap<>();


    public void registerUser(User user) {

        if (users.stream()
                .anyMatch(user1 -> user1.getUserName()
                        .equals(user.getUserName()))) {
            throw new UserNameIsAlreadyTakenException("Username is taken!");
        }
        users.add(user);
    }


    public void addRentable(Rentable vehicle) {
        rentables.add(vehicle);
    }


    public void rent(User user, Rentable rentable, LocalTime time) {
        if (!isRentValid(user, rentable)) {
            throw new IllegalStateException("Something goes to wrong!");
        } else {
            rentable.rent(time);
            actualRenting.put(rentable, user);
        }

    }

    public void closeRent(Rentable rentable, int minutes) {
        if (!actualRenting.containsKey(rentable)) {
            throw new IllegalArgumentException("Not found this rent");
        }
        actualRenting.get(rentable).minusBalance(rentable.calculateSumPrice(minutes));
        actualRenting.remove(rentable);
        rentable.closeRent();
    }


    private boolean isRentValid(User user, Rentable rentable) {
        if (!users.contains(user)) {
            return false;
        }
        if (!rentables.contains(rentable)) {
            return false;
        }
        if (rentable.getRentingTime() != null) {
            return false;
        }
        return true;
    }


    public Set<Rentable> getRentables() {
        return rentables;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Map<Rentable, User> getActualRenting() {
        return actualRenting;
    }
}



package se.lexicon;

public class NameRepository {
    private static String[] names = new String[0];

    // Part 1
    public static int getSize() {
        return names.length;
    }

    public static void setNames(String[] newNames) {
        names = newNames != null ? newNames.clone() : new String[0];
    }

    public static void clear() {
        names = new String[0];
    }

    public static String[] findAll() {
        return names.clone();
    }

    // Part 2
    public static String find(final String fullName) {
        for (String name : names) {
            if (name.equals(fullName)) {
                return name;
            }
        }
        return null;
    }

    public static boolean add(final String fullName) {
        if (find(fullName) != null) {
            return false;
        }
        String[] newNames = new String[names.length + 1];
        System.arraycopy(names, 0, newNames, 0, names.length);
        newNames[names.length] = fullName;
        names = newNames;
        return true;
    }

    // Part 3
    public static String[] findByFirstName(final String firstName) {
        return findByPartName(firstName, true);
    }

    public static String[] findByLastName(final String lastName) {
        return findByPartName(lastName, false);
    }

    private static String[] findByPartName(final String partName, boolean isFirstName) {
        String[] tempArray = new String[names.length];
        int count = 0;
        for (String name : names) {
            String[] splitName = name.split(" ");
            if ((isFirstName && splitName[0].equals(partName)) ||
                    (!isFirstName && splitName.length > 1 && splitName[1].equals(partName))) {
                tempArray[count++] = name;
            }
        }
        String[] result = new String[count];
        System.arraycopy(tempArray, 0, result, 0, count);
        return result;
    }

    public static boolean update(final String original, final String updatedName) {
        int originalIndex = findIndex(original);
        if (originalIndex == -1 || find(updatedName) != null) {
            return false;
        }
        names[originalIndex] = updatedName;
        return true;
    }

    // Part 4
    public static boolean remove(final String fullName) {
        int index = findIndex(fullName);
        if (index == -1) {
            return false;
        }
        String[] newNames = new String[names.length - 1];
        System.arraycopy(names, 0, newNames, 0, index);
        System.arraycopy(names, index + 1, newNames, index, names.length - index - 1);
        names = newNames;
        return true;
    }

    // Helper method to find the index of a name in the array
    private static int findIndex(final String fullName) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(fullName)) {
                return i;
            }
        }
        return -1;
    }
}

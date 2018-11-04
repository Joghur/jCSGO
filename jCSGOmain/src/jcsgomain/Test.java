package jcsgomain;

/**
 *
 * @author martin
 */
public class Test {

    public static void main(String[] args) {
        new Test().go();
    }

    public void go() {

        String str = "[93mDistro Details";
        System.out.println(str);
        str = str.replace("Distro", "");
        System.out.println(str);
    }

}

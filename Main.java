public class Main {
    public static void main(String args[])
    {
        HashMap<Integer,String> map=new HashMap<Integer,String>();
        map.put(1,"pandu");
        map.put(2,"rohit");
        System.out.println(map);
        map.put(1,"ram");
        map.put(3,"sai");
        System.out.println(map);
        map.remove(1);
        System.out.println(map);
        System.out.println(map.get(10));
        System.out.println(map.get(3));
        System.out.println(map.containsKey(5));
    }
}

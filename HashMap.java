public class HashMap<K,V>
{
    private int capacity,size;
    private Node<K,V> map[];

    public HashMap()
    {
        this(11);
    }

    public HashMap(int capacity)
    {
        this.capacity=capacity;
        this.size=0;
        this.map=new Node[capacity];
    }

    private void setCapacity(int capacity)
    {
        this.capacity=capacity;
    }

    private void setMap(int capacity)
    {
        this.map=new Node[capacity];
    }

    public void put(K key,V value)
    {
         if(containsKey(key))
            replace(key,value);
         else
         {
            int index=hashCode(key)%capacity;
            Node<K,V> node=new Node<K,V>(key,value);
            if(map[index]==null)
                map[index]=node;
            else
            {
                node.next=map[index];
                map[index]=node;
            }

            size++;
         }

         double loadFactor=getLoadFactor(size,capacity);
         if(loadFactor>0.75)
             rehash(map,capacity);
         return;
    }

    private double getLoadFactor(int size,int capacity)
    {
        return (1.0*size)/capacity;
    }

    private void rehash(Node<K,V> map[],int capacity)
    {
        int oldCapacity=capacity;
        Node<K,V> oldMap[]=map;

        setCapacity((oldCapacity<<1)+1);
        setMap(capacity);

        for(int i=0;i<oldCapacity;i++)
        {
            if(oldMap[i]!=null)
            {
                 Node<K,V> temp=oldMap[i];
                 while(temp!=null)
                 {
                     put(temp.key,temp.value);
                     temp=temp.next;
                 }
            }
        }
       
        return;
    }

    public boolean containsKey(K key)
    {
        int index=hashCode(key)%capacity;
        Node<K,V> temp=map[index];
        while(temp!=null)
        {
            if(temp.key.equals(key))
                return true;
            temp=temp.next;
        }
        return false;
    }

    private void replace(K key,V value)
    {
        int index=hashCode(key)%capacity;
        Node<K,V> temp=map[index];
        while(temp!=null)
        {
            if(temp.key.equals(key))
            {
                temp.value=value;
                return;
            }
            temp=temp.next;
        }
        return;
    }

    public V remove(K key)
    {
            int index=hashCode(key)%capacity;
            Node<K,V> temp=map[index];
            if(temp.key.equals(key))
            {
                V val=temp.value;
                map[index]=temp.next;
                size--;
                return val;
            }
            else
            {
                Node<K,V> prev=null;
                while(temp!=null)
                {
                    if(temp.key.equals(key))
                    {
                        V val=temp.value;
                        prev.next=temp.next;
                        size--;
                        return val;
                    }
                    prev=temp;
                    temp=temp.next;
                }
            }

            return null;
    }

    public V get(K key)
    {
            int index=hashCode(key)%capacity;
            Node<K,V> temp=map[index];
            while(temp!=null)
            {
                if(temp.key.equals(key))
                    return temp.value;
                temp=temp.next;
            }

        return null;
    }

    private int hashCode(K key)
    {
        String s=String.valueOf(key);
        int hashcode=0,prime=5;
        for(int i=0;i<s.length();i++)
        {
            long v=pow(prime,(i+1));
            hashcode=hashcode+(int)((((int)s.charAt(i))*(v))%1000000007);
        }
        return hashcode;
    }

    private long pow(int x,int y)
    {
         if(y==0)
            return 1L;

         long p=pow(x,y/2);
         if((y&1)==1)
             return (p*p*x)%1000000007;
         else
             return (p*p)%1000000007;
    }

    public String toString()
    {
        String s="{";
        for(int i=0;i<capacity;i++)
        {
            if(map[i]!=null)
            {
                Node<K,V> temp=map[i];
                while(temp!=null)
                {
                     s+=temp.key+"="+temp.value+",";
                     temp=temp.next;
                }
            }
        }
        s+="}";
        return s;
    }
}
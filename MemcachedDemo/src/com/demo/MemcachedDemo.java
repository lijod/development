package com.demo;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;

import net.spy.memcached.MemcachedClient;


public class MemcachedDemo {
   public static void main(String[] args) {
      try{
         // Connecting to Memcached server on localhost
         MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
         System.out.println("Connection to server sucessful.");
         
         Future nameCache = mcc.set("name", 10, new User(1, "Lijo"));
         
         System.out.println("Status:" + nameCache.get());
         

         System.out.println("Cached Name: " + mcc.get("name"));
         
         // Shutdowns the memcached client
         mcc.shutdown();
         
         mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
         System.out.println("Cached Name: " + mcc.get("name"));
         
         Future deleted = mcc.delete("name");
         
         System.out.println("Delete status:" + deleted.get());

         System.out.println("Cached Name: " + mcc.get("name"));
         
         mcc.set("name", 10, new User(1, "Lijo"));
         
         System.out.println("Cached Name: " + mcc.get("name"));
         
         mcc.flush();
         System.out.println("Cached Name: " + mcc.get("name"));
         mcc.shutdown();
      }catch(Exception ex){
         System.out.println( ex.getMessage() );
      }
   }
}
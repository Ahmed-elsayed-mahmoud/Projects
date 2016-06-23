import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	private static final int LRU_REPLCEMEMT = 1;
	private static final int FIFO_REPLCEMEMT = 2;
	private static final int DIRECT_MAPPING = 1;
	private static final int FULLY_ASSOCIATIVE = 2;
	private static final int SET_ASSOCIATIVE = 3;

	public static void main(String[] args) {
		ICache cache = new Cache();
		Mapping mapping = null ;
		
		System.out.println("Enter the cache size (total number of blocks in cache):");
		cache.setCacheSize(scanner.nextInt());
		System.out.println("----------------------------------");
		System.out.println("Enter the mapping function:");
		System.out.println("    1 for Direct mapping.");
        System.out.println("    2 for Fully associative.");
        System.out.println("    3 for Set associative.");
		int choice = scanner.nextInt();
		if(choice == DIRECT_MAPPING){
			cache.setNumOfSets(cache.getCacheSize());
			mapping = new DirectMapping(cache);
		}else if(choice == FULLY_ASSOCIATIVE){
			cache.setNumOfSets(1);
			mapping = new FullyAssociative(cache);
		}else if(choice == SET_ASSOCIATIVE){
			System.out.println("----------------------------------");
			System.out.println("Enter number of sets:");
			cache.setNumOfSets(scanner.nextInt());
			mapping = new SetAssociative(cache);
		}
		cache.createCache();
		System.out.println("----------------------------------");
		System.out.println("Choose the replacement policy:");
		System.out.println("    1 for LRU.");
        System.out.println("    2 for FIFO.");
        choice = scanner.nextInt();
        String replacement = null;
        if(choice == LRU_REPLCEMEMT){
			replacement = new String("LRU");
		}else if(choice == FIFO_REPLCEMEMT){
			replacement = new String("FIFO");
		}
        ReplacementStrategy type = null;
        try {
        	Class<?> method = Class.forName(replacement+"Strategy");
			type = (ReplacementStrategy) method.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
        mapping.setReplacementType(type);
        while(true){
        	System.out.println("----------------------------------");
        	System.out.println("Choose the required action:");
        	System.out.println("    1 Enter sequence of blocks.");
            System.out.println("    2 Print cache contents.");
            System.out.println("    3 Stop simulation");
            choice = scanner.nextInt();
            if(choice == 1){
            	System.out.println("enter the required sequence:(e.g 4,8,5,...)");
    			String input = scanner.next();
    			String[] strArray = input.split(",");
    			int[] blocks = new int[strArray.length];
    			for(int i = 0; i < strArray.length; i++) {
    			    blocks[i] = Integer.parseInt(strArray[i]);
    			}
    			mapping.doMapping(blocks);
    		}else if(choice == 2){
    			cache.printCache();
    		}else if(choice == 3){
    			System.out.println("Simulation Stopped!");
    			System.exit(0);
    		}
        }
		
	}
}

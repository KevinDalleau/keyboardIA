package commun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Helpers {
    public static int[] pickNRandom(int[] array, int n) {
	List<Integer> list = new ArrayList<Integer>(array.length);
        for (int i : array){
		list.add(i);
        }
	Collections.shuffle(list);
        
        int[] answer = new int[n];
	for (int i = 0; i < n; i++){
            answer[i] = list.get(i);
	}
	return answer;

    }
}

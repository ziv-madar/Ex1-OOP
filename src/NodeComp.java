package ex1;

import java.util.Comparator;

public class NodeComp implements Comparator<node_info> {
	@Override
	public int compare (node_info n1, node_info n2) {
		int ans = (int) (n2.getTag()*1000.0- n1.getTag()*1000.0);
        if(ans < 0)
        	ans = 1; //n1 is bigger
        else if (ans > 0)
        	ans = -1; //n2 is bigger

        // else {n2-n1 = 0 :they are equal}
        return ans;
	}
}

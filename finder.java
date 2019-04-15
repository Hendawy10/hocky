package eg.edu.alexu.csd.datastructure.iceHockey;

import java.util.Scanner;
public class finder {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String[] s = new String[10000];
		s[1] = input.next();
		int x ;
		int y;
		int count = 1;
		while (s[count].charAt(s[count].length() - 1) != '.') {
			count++;
			s[count] = input.next();
		}
		x=input.nextInt();
		y=input.nextInt();
		IPlayersFinder finder=new photo();
		finder.findPlayers(s,x , y);
	}

}

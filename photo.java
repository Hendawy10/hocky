package eg.edu.alexu.csd.datastructure.iceHockey;

import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class photo implements IPlayersFinder{
	static HashSet<Point> dots = new HashSet<Point>();
	 static int A = 0;
	 static int xmax = -1;
	 static int xmin = 10000;
	 static int ymax = -1;
	 static int ymin = 10000;
	
	public void positionD(int i, int j, String[] photo, char team) {
		Point p = new Point(i, j);
		if (photo[i] == null) return;
	
		if (j >= photo[1].length() || j < 0) return;

		if (photo[i].charAt(j) != team) return;
		
		if (dots.contains(p)) return;
		
		xmax = Math.max(2 * (j - 1) + 2, xmax);
		ymax = Math.max(2 * i, ymax);
		xmin = Math.min(2 * (j - 1), xmin);
		ymin = Math.min(2 * i - 2, ymin);
		
		dots.add(p);
		A += 4;
		
		positionD(i + 1, j, photo, team);
		positionD(i - 1, j, photo, team);
		positionD(i, j + 1, photo, team);
		positionD(i, j - 1, photo, team);

	}
	
	public Point[] findPlayers(String[] photo, int team, int threshold) {
		// TODO Auto-generated method stub
		Point[] answer = new Point[1000];
		int count = 0;
		int i = 1;
		while (photo[i] != null) {
			for (int j = 0; j < photo[i].length(); j++) {
				A = 0;
				xmax = -1;
				xmin = 10000;
				ymax = -1;
				ymin = 10000;
				char t = photo[i].charAt(j);
				Point a = new Point(i, j);
				int q = team;
				q = (char) (q + '0');//0 for ascci
				if (t == q && !dots.contains(a)) {
					positionD(i, j, photo, t);
					if (A >= threshold) {
						int x = (((xmax + xmin)/2)+2);
						int y = (ymax + ymin) / 2 ;
						answer[count] = new Point(x,y);
						count++;
					}
				}
			}
			i++;
		}
		
		Arrays.sort(answer, new Comparator<Point>() {
			public int compare(Point a, Point b) {
				if (a == null || b == null) {
					return 0;
				}
				int cx = Integer.compare(a.x, b.x);
				if (cx == 0)
					return Integer.compare(a.y, b.y);
				else
					return cx;
			}
		});
		
		for (i = 0; i < count; i++) {
			System.out.println(answer[i]);
		}
		return answer;
	}

}

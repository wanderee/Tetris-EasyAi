package Game;

import java.util.ArrayList;

import Pattern.TetrisLattice;

public class TetrisAI {
	private int width;
	private int height;
	private TetrisLattice AILattice;
	private int[] ScoreRow ;
	private int ScoreSide = 100;
	private int ScoreUP = 5000;
	private int Scoreblock = 1000;
	public ArrayList<Integer> HandlerList = new ArrayList<Integer>();
	public TetrisAI(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		ScoreRow = new int[height];
		setScoreRow();
	}
	public void test_print(int a) {
		System.out.println(a);
	}
	public void calcWay(TetrisLattice lattices) {
		HandlerList.clear();
		//System.out.println(HandlerList);
		int minScore = 0x7FFFFFFF;
		//int maxScore = 0;
		int handler = 0;
		int k = 0;
		int typetimes = 0;
		for(int i= -width+1;i<width;i++) {
			AILattice = lattices.copy();
			int type = AILattice.getPatternType();
			//boolean c = true;
			//if(!AILattice.Handler(10)) {type = 1;c=false;}
			for(int t = 0;t<type;t++) {
				k=i;
				AILattice = lattices.copy();
				for(int kt = 0;kt<t;kt++) {
					AILattice.Handler(0);
				}
				while(k!=0){
					if(k<0) {
						AILattice.Handler(3);
						k++;
					}
					else {
						AILattice.Handler(1);
						
						k--;
					}
				}
				while(!AILattice.BlockFall());
				int Score = calcScore();
				
				if(minScore>Score) {
//					AILattice.print_Lattices();
//					test_print(Score);
//					test_print(i);
//					test_print(t);
					minScore = Score;
					handler = i;
					typetimes = t;
				}
			}
		}
//		test_print(10000);
//		test_print(minScore);
//		test_print(handler);
//		test_print(typetimes);
		
		for(int i=0;i<typetimes;i++) {
			HandlerList.add(0);
		}
		if(handler<0) {
			HandlerList.add(3);
		}
		else if(handler>0) {
			HandlerList.add(1);
		}

	}
	private int calcScore() {
		int score = 0;
		
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				if(AILattice.Lattices[i][j]==2) {
					score+= Scoreblock-ScoreRow[i];
				}
				else if(AILattice.Lattices[i][j]==0) {
					//score += ScoreRow[i];
					score += getsurroundScore(i, j);
				}
			}
		}
		return score;
	}
	private void setScoreRow() {
		//ScoreRow数组的循序与Lattice的循序一致
		for(int i=0;i<height;i++) {
			ScoreRow[i] = i*10;
		}
	}
	private int getsurroundScore(int y,int x) {
		int score = 0;
		if(x==0) {
			score += ScoreSide;
			if(AILattice.Lattices[y][x+1]==2) {
				score += ScoreSide;
			}
			
		}
		else if(x==(width-1)) {
			score += ScoreSide;
			if(AILattice.Lattices[y][x-1]==2) {
				score += ScoreSide;
			}
		}
		else {
			if(AILattice.Lattices[y][x-1]==2) {
				score += ScoreSide;
			}
			if(AILattice.Lattices[y][x+1]==2) {
				score += ScoreSide;
			}
		}
		
		if(y!=0&&AILattice.Lattices[y-1][x]==2) {
			score += ScoreUP;
		}
		
		return score;
	}
	
}

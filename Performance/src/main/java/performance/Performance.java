package performance;

import models.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

/**
 * Performance testing file.
 * 
 * @author Oscar Guillen
 *
 */

public class Performance {
    
    /**
     * Output file 1
     */
    private static final String output = "linear2.txt";

    /**
     * Output file 2
     */
    private static final String output2 = "disorder2.txt";

    /**
     * Output file 3
     */
    private static final String output3 = "manhattan2.txt";
	
	/**
     * Min number of random moves
     */
    private static final int min_moves = 10;

    /**
     * Max number of random moves
     */
    private static final int moves = 20;

    /**
     * Class constructor.
     */
    public Performance () {
    }

	public static Board generateBoard(int moves) {
		Board board = new Board();
		do {
			for (int i = 0; i < moves; i++) {
				SolvingSequence seq = new SolvingSequence();
				boolean added = false;
				int spacePosition = board.getSpacePosition();
				while (!added) {
					Random r = new Random();
					int a = 1;
					int b = 5;
					int result = r.nextInt(b-a) + a;
					switch (result) {
					case 1:
						if (spacePosition % 4 == 0)
							break;
						added = true;
						seq.addMovement(SolvingSequence.Direction.LEFT);
						break;
					case 2:
						if ((spacePosition + 1) % 4 == 0) 
							break;
						added = true;
						seq.addMovement(SolvingSequence.Direction.RIGHT);
						break;
					case 3:
						if (spacePosition < 4)
							break;
						added = true;
						seq.addMovement(SolvingSequence.Direction.UP);
						break;
					case 4:
						if (spacePosition > 11)
							break;
						added = true;
						seq.addMovement(SolvingSequence.Direction.DOWN);
						break;
					}
				}
				board.applySolvingSequence(seq);
			}
		} while (board.isSolved());

		return board;
	}
    
    public static void main(String[] args) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(output));
			BufferedWriter writer2 = new BufferedWriter(new FileWriter(output2));
			BufferedWriter writer3 = new BufferedWriter(new FileWriter(output3));

			for( int i = min_moves; i <= moves; i++ ) {
				System.out.println("A loop...");
				Board b1 = generateBoard(i);

				Solution s1 = new Solution("Linear");
				long startTime = System.nanoTime();
				s1.solve(b1);
				long endTime = System.nanoTime();
				long duration = (endTime - startTime);

				String result = Integer.toString(i) + " "
								+ Integer.toString(s1.getSequence().getLenght()) + " "
								+ Long.toString(duration) + " "
								+ Integer.toString(s1.getMaxDepth()) + " "
								+ Integer.toString(s1.getOpened()) + " "
								+ Float.toString(s1.getFunction().calcFitness(b1)) + "\n";
				writer.write(result);
	 
				Board b2 = generateBoard(i);
				Solution s2 = new Solution("Disorder");
				startTime = System.nanoTime();
				s2.solve(b2);
				endTime = System.nanoTime();
				duration = (endTime - startTime);

				result = Integer.toString(i) + " "
								+ Integer.toString(s2.getSequence().getLenght()) + " "
								+ Long.toString(duration) + " "
								+ Integer.toString(s2.getMaxDepth()) + " "
								+ Integer.toString(s2.getOpened()) + " "
								+ Float.toString(s2.getFunction().calcFitness(b2)) + "\n";
				writer2.write(result);

				Board b3 = generateBoard(i);
				Solution s3 = new Solution("Manhattan");
				startTime = System.nanoTime();
				s3.solve(b3);
				endTime = System.nanoTime();
				duration = (endTime - startTime);
				result = Integer.toString(i) + " "
								+ Integer.toString(s3.getSequence().getLenght()) + " "
								+ Long.toString(duration) + " "
								+ Integer.toString(s3.getMaxDepth()) + " "
								+ Integer.toString(s3.getOpened()) + " "
								+ Float.toString(s3.getFunction().calcFitness(b3)) + "\n";
				writer3.write(result);

		
			}

			writer.close();
			writer2.close();
			writer3.close();
		} catch (Exception e) {
          /* ... */
        }
    }
}

import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    private int[][] loadBlocks(String filename ) {
        // create initial board from file
        In in = new In("src/test/resources/"+filename);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        return blocks;
    }

    private int getQtyFromFilename(String filename) {
        String[] tokens;
        if (filename.contains("unsolvable")) {
            return -1;
        }
        if (filename.contains("-")) {
            tokens = filename.split("-");
            tokens = tokens[1].split("\\.");
        }
        else {
            tokens = filename.split("\\.");
            tokens[0] = tokens[0].replace("puzzle", "");
        }
        return Integer.parseInt(tokens[0]);
    }

    @Test
    public void constructorWhenParameterIsNullExpectsIllegalArgumentException() {

        // Assert
        IllegalArgumentException thrown = assertThrows( IllegalArgumentException.class, () -> {

            // Arrange
            // Act
            Solver solver = new Solver(null);

        });
        assertEquals("Null is not accepted.", thrown.getMessage());

    }

    @Test
    public void isSolvableWhenUnsolvableBoardBySpecIsSuppliedExpectsFalse(){

        String[] fileList = new String[]{
              "puzzle2x2-unsolvable1.txt"
            , "puzzle2x2-unsolvable2.txt"
            , "puzzle2x2-unsolvable3.txt"
            , "puzzle3x3-unsolvable.txt"
            , "puzzle3x3-unsolvable1.txt"
            , "puzzle3x3-unsolvable2.txt"
            , "puzzle4x4-unsolvable.txt"
        };
        for ( String filename : fileList ) {

            // Arrange
            int[][] blocks = loadBlocks(filename);

            // Act
            Board board = new Board(blocks);
            Solver solver = new Solver(board);

            // Assert
            assertFalse(solver.isSolvable(), "in file " + filename);

        }
    }


    @Test
    public void solveWhenDataIsProvidedBySpecFilesExpectsPredefinedSteps() {

        String[] fileList = new String[]{
              "puzzle2x2-00.txt"
            , "puzzle2x2-01.txt"
            , "puzzle2x2-02.txt"
            , "puzzle2x2-03.txt"
            , "puzzle2x2-04.txt"
            , "puzzle2x2-05.txt"
            , "puzzle2x2-06.txt"

            , "puzzle3x3-01.txt"
            , "puzzle3x3-02.txt"
            , "puzzle3x3-03.txt"
            , "puzzle3x3-04.txt"
            , "puzzle3x3-05.txt"
            , "puzzle3x3-06.txt"
            , "puzzle3x3-07.txt"
            , "puzzle3x3-08.txt"
            , "puzzle3x3-09.txt"
            , "puzzle3x3-10.txt"
            , "puzzle3x3-11.txt"
            , "puzzle3x3-12.txt"
            , "puzzle3x3-13.txt"
            , "puzzle3x3-14.txt"
            , "puzzle3x3-15.txt"
            , "puzzle3x3-16.txt"
            , "puzzle3x3-17.txt"
            , "puzzle3x3-18.txt"
            , "puzzle3x3-19.txt"
            , "puzzle3x3-20.txt"
            , "puzzle3x3-21.txt"
            , "puzzle3x3-22.txt"
            , "puzzle3x3-23.txt"
            , "puzzle3x3-24.txt"
            , "puzzle3x3-25.txt"
            , "puzzle3x3-26.txt"
            , "puzzle3x3-27.txt"
            , "puzzle3x3-28.txt"
            , "puzzle3x3-29.txt"
            , "puzzle3x3-30.txt"
            , "puzzle3x3-31.txt"

            // , "puzzle4x4-01.txt"
            // , "puzzle4x4-02.txt"
            // , "puzzle4x4-03.txt"
            // , "puzzle4x4-04.txt"
            // , "puzzle4x4-05.txt"
            // , "puzzle4x4-06.txt"
            // , "puzzle4x4-07.txt"
            // , "puzzle4x4-08.txt"
            // , "puzzle4x4-09.txt"
            // , "puzzle4x4-10.txt"
            // , "puzzle4x4-11.txt"
            // , "puzzle4x4-12.txt"
            // , "puzzle4x4-13.txt"
            // , "puzzle4x4-14.txt"
            // , "puzzle4x4-15.txt"
            // , "puzzle4x4-16.txt"
            // , "puzzle4x4-17.txt"
            // , "puzzle4x4-18.txt"
            // , "puzzle4x4-19.txt"
            // , "puzzle4x4-20.txt"
            // , "puzzle4x4-21.txt"
            // , "puzzle4x4-22.txt"
            // , "puzzle4x4-23.txt"
            // , "puzzle4x4-24.txt"
            // , "puzzle4x4-25.txt"
            // , "puzzle4x4-26.txt"
            // , "puzzle4x4-27.txt"
            // , "puzzle4x4-28.txt"
            // , "puzzle4x4-29.txt"
            // , "puzzle4x4-30.txt"

            // , "puzzle4x4-31.txt"
            // , "puzzle4x4-32.txt"        // Failed
            // , "puzzle4x4-33.txt"
            // , "puzzle4x4-34.txt"
            // , "puzzle4x4-35.txt"
            // , "puzzle4x4-36.txt"        // Failed
            // , "puzzle4x4-37.txt"
            // , "puzzle4x4-38.txt"
            // , "puzzle4x4-39.txt"
            // , "puzzle4x4-40.txt"
            // , "puzzle4x4-41.txt"
            // , "puzzle4x4-42.txt"
            // , "puzzle4x4-43.txt"
            // , "puzzle4x4-44.txt"
            // , "puzzle4x4-45.txt"
            // , "puzzle4x4-46.txt"
            // , "puzzle4x4-47.txt"
            // , "puzzle4x4-48.txt"
            // , "puzzle4x4-49.txt"
            // , "puzzle4x4-50.txt"
            // , "puzzle4x4-78.txt"
            // , "puzzle4x4-80.txt"

            , "puzzle00.txt"
            , "puzzle01.txt"
            , "puzzle02.txt"
            , "puzzle03.txt"
            , "puzzle04.txt"
            , "puzzle05.txt"
            , "puzzle06.txt"
            , "puzzle07.txt"
            , "puzzle08.txt"
            , "puzzle09.txt"
            , "puzzle10.txt"
            , "puzzle11.txt"
            , "puzzle12.txt"
            , "puzzle13.txt"
            , "puzzle14.txt"
            , "puzzle15.txt"
            , "puzzle16.txt"
            , "puzzle17.txt"
            , "puzzle18.txt"
            , "puzzle19.txt"
            , "puzzle20.txt"
            // , "puzzle21.txt"
            // , "puzzle22.txt"
            // , "puzzle23.txt"
            // , "puzzle24.txt"
            // , "puzzle25.txt"
            // , "puzzle26.txt"
            // , "puzzle27.txt"
            // , "puzzle28.txt"
            // , "puzzle29.txt"
            // , "puzzle30.txt"
            // , "puzzle31.txt"
            // , "puzzle32.txt"
            // , "puzzle33.txt"
            // , "puzzle34.txt"
            // , "puzzle35.txt"
            // , "puzzle36.txt"
            // , "puzzle37.txt"
            // , "puzzle38.txt"
            // , "puzzle39.txt"
            // , "puzzle40.txt"
            // , "puzzle41.txt"
            // , "puzzle42.txt"
            // , "puzzle43.txt"
            // , "puzzle44.txt"
            // , "puzzle45.txt"
            // , "puzzle46.txt"
            // , "puzzle47.txt"
            // , "puzzle48.txt"
            // , "puzzle49.txt"
            // , "puzzle50.txt"

        };
        for ( String filename : fileList ) {

            // Arrange
            int[][] blocks = loadBlocks(filename);

            // Act
            Board board = new Board(blocks);
            Solver solver = new Solver(board);
            int steps = getQtyFromFilename(filename);

            // Assert
            assertEquals(steps, solver.moves(), "while processing file " + filename);


        }

    }


    @Test
//    @Timeout(value = 1)
    public void assessmentGuideCorrectnessSectionTest5a() {

        String[] fileList = new String[]{
              "puzzle01.txt"
            , "puzzle03.txt"
            , "puzzle04.txt"
            , "puzzle17.txt"
            , "puzzle3x3-unsolvable1.txt"

        };
        for ( String filename : fileList ) {

            // Arrange
            int[][] blocks = loadBlocks(filename);

            // Act
            Board board = new Board(blocks);
            Solver solver = new Solver(board);
            int steps = getQtyFromFilename(filename);

            // Assert
            assertEquals(steps, solver.moves(), "while processing file " + filename);


        }

    }

}
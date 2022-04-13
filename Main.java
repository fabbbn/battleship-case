import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Position {
    private static final int max_position = 9; // define max position as the max size is 10
    int x_axis;
    int y_axis;
    public Position(int x, int y) {
        if(validatePosition(x,y)) {
            this.x_axis = x;
            this.y_axis = y;
        }
        else {
            // option: throw new exception: out of bound
            System.out.format("The position [%d,%d] is out of arena", x, y);
            System.exit(0);
        }
    }
    boolean validatePosition(int x, int y) {
        return (
                x>=0 && x<=max_position && y>=0 && y<=max_position
        );
    }
    public boolean comparePositions(Position comparison) {
        return (this.x_axis==comparison.x_axis && this.y_axis==comparison.y_axis);
    }
}

class BattleField {
    char[][] field;
    public BattleField(int size){
        if(validateFieldSize(size)) {
            // create battlefield
            this.field = new char[size][size];
            for (char[] row:this.field) {
                Arrays.fill(row, '_');
            }
        }
        else {
            // option: throw new exception: out of bound
            System.out.println("The size of field is invalid. It must be greater than 0 and less than 10");
            System.exit(0);
        }
    }
    // assume we can access this feature without assign any object first
    public static boolean validateFieldSize(int size) {
        return (size<10&&size>0);
    }
    public boolean isNotFilledAt(Position position) {
        return (this.field[position.x_axis][position.y_axis]=='_');
    }
    void getHitByOpponentAt(Position spot) {
        this.field[spot.x_axis][spot.y_axis] = 'X';
    }
    void aliveShipAt(Position spot) {
        this.field[spot.x_axis][spot.y_axis] = 'B';
    }
    void missileMissedAt(Position spot) {
        this.field[spot.x_axis][spot.y_axis] = 'O';
    }
    public void printBattleField() {
        for (char[] chars : this.field) {
            for (int j = 0; j < this.field[0].length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
    }
}

class Player {
    BattleField arena;
    int num_missile;
    int num_ships;
    ArrayList<Position> ship_positions = new ArrayList<>();
    ArrayList<Position> missile_positions = new ArrayList<>();
    int num_hit;

    // constructor
    public Player(
            int arena_size,
            int num_missile,
            int num_ships,
            String ship_positions,
            String missile_positions
    ) {
        if (validateData(arena_size, num_ships, num_missile)) {
            this.arena = new BattleField(arena_size);
            this.num_ships = num_ships;
            this.num_missile = num_missile;
            this.num_hit = 0;
            // process the string
            this.ship_positions = parsePositionString(ship_positions);
            this.missile_positions = parsePositionString(missile_positions);
        }
        else {
            System.out.println("The number of ships/missiles is out of range");
            System.exit(0);
        }
    }

    boolean validateData(int field_size, int num_ships, int num_missile) {
        double maximumNum = (field_size*field_size >> 2);
        return (num_ships <= maximumNum && num_missile < 100 && num_missile > 0 );
    }

    void startAttack(Player opponent) {
        for (Position ship_spot: this.ship_positions) {
            for (int j = 0; j < opponent.missile_positions.size(); j++) {
                if (ship_spot.comparePositions(opponent.missile_positions.get(j))) {
                    this.arena.getHitByOpponentAt(ship_spot);
                    opponent.num_hit++;
                }
                else if (this.arena.isNotFilledAt(ship_spot)) {
                    this.arena.aliveShipAt(ship_spot);
                }
                if(!this.ship_positions.contains(opponent.missile_positions.get(j)) && this.arena.isNotFilledAt(opponent.missile_positions.get(j))) {
                    this.arena.missileMissedAt(opponent.missile_positions.get(j));
                }
            }
        }
    }
    // util function to parse the position string
    ArrayList<Position> parsePositionString(String positions) {
        ArrayList<Position> result = new ArrayList<>();
        for (String position:
                positions.split((":"))) {
            String[] axis = position.split(",");
            int x = Integer.parseInt(axis[0]);
            int y = Integer.parseInt(axis[1]);
            result.add(new Position(x,y));
        }
        return result;
    }
}

class Game {
    Player P1;
    Player P2;
    Game(Player p1, Player p2) {
        this.P1 = p1;
        this.P2 = p2;
    }

    public void startGame() {
        this.P1.startAttack(P2);
        this.P2.startAttack(P1);
    }
    public void printResult() {
        System.out.println("Player1");
        this.P1.arena.printBattleField();
        System.out.println();
        System.out.println("Player2");
        this.P2.arena.printBattleField();
        System.out.println();

        System.out.printf("P1:%d\n",this.P1.num_hit);
        System.out.printf("P2:%d\n",this.P2.num_hit);
        if(this.P1.num_hit==this.P2.num_hit) {
            System.out.println("It is a draw");
        }
        else if (this.P1.num_hit>this.P2.num_hit) {
            System.out.println("Player 1 wins");
        }
        else {
            System.out.println("Player 2 wins");
        }
    }
}
public class Main {
    public static void main(String[] args) {
        // get inputs
        Scanner in = new Scanner(System.in);
        // size of battleship
        int M = in.nextInt();

        // number of ships each player has
        int S = in.nextInt();

        // positions of ships
        String ships_player1 = in.next();
        String ships_player2 = in.next();

        // number of missiles each player has
        int T = in.nextInt();

        // missiles position
        String missiles_player1 = in.next();
        String missiles_player2 = in.next();

        // initialize player
        Player P1 = new Player(M, T, S, ships_player1, missiles_player1);
        Player P2 = new Player(M, T, S, ships_player2, missiles_player2);

        Game newGame = new Game(P1, P2);
        newGame.startGame();

        newGame.printResult();

    }
}
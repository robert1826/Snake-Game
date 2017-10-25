package player;

import java.util.Scanner;

public class CommandLineHumanPlayer extends Player {


	@Override
	public int getInputDirectionCode() {
		return CommandLineInputGetter.getInput();
	}

	private static class CommandLineInputGetter{
		private static final Scanner sc = new Scanner(System.in);

		private static int getInput() {
			char s = sc.next().charAt(0);
			while(s != 'u' && s != 'r' && s != 'd' && s != 'l'){
				System.out.println("Please enter valid input :");
				s = sc.next().charAt(0);
			}

			return "urdl".indexOf(s);
		}
	}

	@Override
	protected boolean getIsGuiPlayer() {
		return false;
	}
}

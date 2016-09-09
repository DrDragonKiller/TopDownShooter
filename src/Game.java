import javax.swing.*;

public class Game {

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		JLabel jl = new JLabel(new ImageIcon("./bin/dog.jpg"));
		JLabel jl2 = new JLabel(new ImageIcon("./bin/dog.jpg"));

		jl2.setSize(200, 200);
		jf.getContentPane().add(jl2);
		jf.getContentPane().add(jl);

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);

	}

}

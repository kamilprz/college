import javax.swing.JOptionPane;
public class VertabrateClass {

	public static void main(String[] args) {
		int answerBlood = JOptionPane.showConfirmDialog
				(null,"Is the animal warm blooded?");
		boolean warmBlood = (answerBlood==JOptionPane.YES_OPTION);
		if (warmBlood)
		{
			int answerFeathers = JOptionPane.showConfirmDialog 
				(null, "Does it have feathers?");
			boolean feathers = (answerFeathers==JOptionPane.YES_OPTION);
			if (feathers)
			{
				JOptionPane.showMessageDialog(null, "It is a bird.");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "It is a mammal.");
			}
		}
		else
		{
			int answerScales = JOptionPane.showConfirmDialog
					(null,"Does it have scales?");
			boolean scales = (answerScales==JOptionPane.YES_OPTION);
			if (scales==false)
				JOptionPane.showMessageDialog(null, "It is an amphibian.");
			else
			{
				int answerFins = JOptionPane.showConfirmDialog
						(null, "Does it have fins?");
				boolean fins = (answerFins==JOptionPane.YES_OPTION);
				if (fins)
					JOptionPane.showMessageDialog(null,"It is a fish");
				else
					JOptionPane.showMessageDialog(null,"It is a reptile");
			}
		}
	}

}

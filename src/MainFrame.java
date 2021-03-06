import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class MainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private String daAnalizzare;
	private Main main;

	public MainFrame(Main main2){
		this.main = main2;
		JButton summary = new JButton("Build a fairy tale!");
		JTextPane text = new JTextPane();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		text.setText("Once upon a time there was a sweet little girl. Everyone who saw her liked her, but most of all her grandmother, who did not know what to give the child next. Once she gave her a little cap made of red velvet. Because it suited her so well, and she wanted to wear it all the time, she came to be known as Little Red Riding Hood. One day her mother said to her: \"Come Little Red Riding Hood. Here is a piece of cake and a bottle of wine. Take them to your grandmother. She is sick and weak, and they will do her well. Mind your manners and give her my greetings. Behave yourself on the way, and do not leave the path, or you might fall down and break the glass, and then there will be nothing for your sick grandmother.\" Little Red Riding Hood promised to obey her mother. The grandmother lived out in the woods, a half hour from the village. When Little Red Riding Hood entered the woods a wolf came up to her. She did not know what a wicked animal he was, and was not afraid of him. \"Good day to you, Little Red Riding Hood.\" - \"Thank you, wolf.\" - \"Where are you going so early, Little Red Riding Hood?\" - \"To grandmother's.\" - \"And what are you carrying under your apron?\" - \"Grandmother is sick and weak, and I am taking her some cake and wine. We baked yesterday, and they should give her strength.\" - \"Little Red Riding Hood, just where does your grandmother live?\" - \"Her house is a good quarter hour from here in the woods, under the three large oak trees. There's a hedge of hazel bushes there. You must know the place,\" said Little Red Riding Hood. The wolf thought to himself: \"Now there is a tasty bite for me. Just how are you going to catch her?\" Then he said: \"Listen, Little Red Riding Hood, haven't you seen the beautiful flowers that are blossoming in the woods? Why don't you go and take a look? And I don't believe you can hear how beautifully the birds are singing. You are walking along as though you were on your way to school in the village. It is very beautiful in the woods.\" Little Red Riding Hood opened her eyes and saw the sunlight breaking through the trees and how the ground was covered with beautiful flowers. She thought: \"If a take a bouquet to grandmother, she will be very pleased. Anyway, it is still early, and I'll be home on time.\" And she ran off into the woods looking for flowers. Each time she picked one she thought that she could see an even more beautiful one a little way off, and she ran after it, going further and further into the woods. But the wolf ran straight to the grandmother's house and knocked on the door. \"Who's there?\" - \"Little Red Riding Hood. I'm bringing you some cake and wine. Open the door for me.\" - \"Just press the latch,\" called out the grandmother. \"I'm too weak to get up.\" The wolf pressed the latch, and the door opened. He stepped inside, went straight to the grandmother's bed, and ate her up. Then he took her clothes, put them on, and put her cap on his head. He got into her bed and pulled the curtains shut. Little Red Riding Hood had run after flowers, and did not continue on her way to grandmother's until she had gathered all that she could carry. When she arrived, she found, to her surprise, that the door was open. She walked into the parlor, and everything looked so strange that she thought: \"Oh, my God, why am I so afraid? I usually like it at grandmother's.\" Then she went to the bed and pulled back the curtains. Grandmother was lying there with her cap pulled down over her face and looking very strange. \"Oh, grandmother, what big ears you have!\" - \"All the better to hear you with.\" - \"Oh, grandmother, what big eyes you have!\" - \"All the better to see you with.\" - \"Oh, grandmother, what big hands you have!\" - \"All the better to grab you with!\" - \"Oh, grandmother, what a horribly big mouth you have!\" - \"All the better to eat you with!\" And with that he jumped out of bed, jumped on top of poor Little Red Riding Hood, and ate her up. As soon as the wolf had finished this tasty bite, he climbed back into bed, fell asleep, and began to snore very loudly. A huntsman was just passing by. He thought it strange that the old woman was snoring so loudly, so he decided to take a look. He stepped inside, and in the bed there lay the wolf that he had been hunting for such a long time. \"He has eaten the grandmother, but perhaps she still can be saved. I won't shoot him,\" thought the huntsman. So he took a pair of scissors and cut open his belly. He had cut only a few strokes when he saw the red cap shining through. He cut a little more, and the girl jumped out and cried: \"Oh, I was so frightened! It was so dark inside the wolf's body!\" And then the grandmother came out alive as well. Then Little Red Riding Hood fetched some large heavy stones. They filled the wolf's body with them, and when he woke up and tried to run away, the stones were so heavy that he fell down dead. The three of them were happy. The huntsman took the wolf's pelt. The grandmother ate the cake and drank the wine that Little Red Riding Hood had brought. And Little Red Riding Hood thought to herself: \"As long as I live, I will never leave the path and run off into the woods by myself if mother tells me not to.\"");
		setLayout(new BorderLayout());
		add(text, BorderLayout.CENTER);
		add(summary, BorderLayout.SOUTH);
		summary.addActionListener(chooseFiles());
		setVisible(true);
	}
	
	public String getInputText(){
		return daAnalizzare;
	}
	
	private ActionListener chooseFiles() {
		final JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("File di testo (.txt/.text)", "txt", "text");
		chooser.setFileFilter(filter);
		chooser.setAcceptAllFileFilterUsed(false);
		ActionListener toReturn = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				daAnalizzare = "";
				int returnVal = chooser.showOpenDialog(chooser);
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		        	File file = chooser.getSelectedFile();
		        	try {
		        		List<String> linee = Files.readAllLines(file.toPath(), Charset.defaultCharset());
						for(String x: linee)
							daAnalizzare+=x;
						daAnalizzare+=" ";
						
						synchronized(main){
							main.notify();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					  }
		        }
			}
		};
		return toReturn;
	}
}

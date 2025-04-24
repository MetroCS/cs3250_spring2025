import java.io.InputStream;
import java.util.*;


/**
* A conventional Hangman game playable from the command line.
* Implements:
*  - 6 incorrect guesses → complete hangman (head, body, arms, legs)
*  - Guess single letters or full word (costs 1 try on wrong)
*  - Repeated or non‑alphabetic guesses are ignored
*  - Case‑insensitive; words drawn from a small hardcoded list
*  - play() returns Optional.of(score) where score = remaining tries on win, or 0 on loss
*/
public class HangmanGame implements Game {
   //─── config ────────────────────────────────────────────────────────────────
   private static final int MAX_TRIES = 6;
   private static final List<String> WORDS = Arrays.asList(
       "apple", "banana", "cherry", "dragon", "elephant", "falcon"
   );
   private static final String[] HANGMAN_STATES = {
       " +---+\n     |\n     |\n     |\n    ===",
       " +---+\n O   |\n     |\n     |\n    ===",
       " +---+\n O   |\n |   |\n     |\n    ===",
       " +---+\n O   |\n/|   |\n     |\n    ===",
       " +---+\n O   |\n/|\\  |\n     |\n    ===",
       " +---+\n O   |\n/|\\  |\n/    |\n    ===",
       " +---+\n O   |\n/|\\  |\n/ \\  |\n    ==="
   };


   //─── instance state ────────────────────────────────────────────────────────
   private String secret;
   private final Set<Character> guessedLetters = new LinkedHashSet<>();
   private int remainingTries;
   private boolean fullWordGuessed;
   private final Scanner scanner;


   //─── constructors ────────────────────────────────────────────────────────────


   /** Default—uses System.in and picks a random hardcoded word */
   public HangmanGame() {
       this(System.in, WORDS.get(new Random().nextInt(WORDS.size())));
   }


   /** Parameterized for injection and testing; picks your provided secret */
   public HangmanGame(InputStream in, String secret) {
       this.scanner = new Scanner(in);
       setSecretWord(secret);
       this.remainingTries  = MAX_TRIES;
       this.fullWordGuessed = false;
   }


   //─── Game interface ─────────────────────────────────────────────────────────


   /** Name of this game, used by the launcher/menu */
   @Override
   public String getName() {
       return "Hangman";
   }


   /**
    * Runs the CLI loop. Returns the final score in an Optional:
    * remainingTries if won, or 0 if lost.
    */
   @Override
   public Optional<Integer> play() {
       while (!isWon() && !isLost()) {
           int wrong = MAX_TRIES - remainingTries;
           System.out.println(HANGMAN_STATES[wrong]);
           System.out.println("Word:    " + getMaskedWord());
           System.out.println("Guessed: " + guessedLetters);
           System.out.print("Enter letter or word: ");


           String input = scanner.nextLine().trim();
           boolean correct;
           if (input.length() == 1) {
               correct = guessLetter(input.charAt(0));
           } else {
               correct = guessWord(input);
           }
           System.out.println(correct ? "✅ Correct!" : "❌ Wrong!");
           System.out.println();
       }


       if (isWon()) {
           System.out.println("🎉 You won! The word was: " + secret);
           return Optional.of(remainingTries);
       } else {
           System.out.println(HANGMAN_STATES[MAX_TRIES]);
           System.out.println("💀 You lost. The word was: " + secret);
           return Optional.of(0);
       }
   }


   //─── core logic ─────────────────────────────────────────────────────────────


   public boolean guessLetter(char ch) {
       ensureActive();
       ch = Character.toLowerCase(ch);
       if (ch < 'a' || ch > 'z') {
           return false;
       }
       if (guessedLetters.contains(ch)) {
           return false;
       }
       guessedLetters.add(ch);
       if (secret.indexOf(ch) >= 0) {
           return true;
       } else {
           remainingTries--;
           return false;
       }
   }


   public boolean guessWord(String attempt) {
       ensureActive();
       if (attempt == null || attempt.trim().isEmpty()) {
           return false;
       }
       if (attempt.trim().equalsIgnoreCase(secret)) {
           fullWordGuessed = true;
           return true;
       } else {
           remainingTries--;
           return false;
       }
   }


   //─── state queries ──────────────────────────────────────────────────────────


   public boolean isWon() {
       if (fullWordGuessed) return true;
       for (char c : secret.toCharArray()) {
           if (!guessedLetters.contains(c)) {
               return false;
           }
       }
       return true;
   }


   public boolean isLost() {
       return remainingTries <= 0 && !isWon();
   }


   public String getMaskedWord() {
       StringBuilder sb = new StringBuilder();
       for (char c : secret.toCharArray()) {
           sb.append(guessedLetters.contains(c) ? c : '_').append(' ');
       }
       return sb.toString().trim();
   }


   //─── test helpers ───────────────────────────────────────────────────────────


   Set<Character> getGuessedLetters() {
       return new LinkedHashSet<>(guessedLetters);
   }


   int getRemainingTries() {
       return remainingTries;
   }


   void setSecretWord(String newSecret) {
       if (newSecret == null
           || newSecret.trim().isEmpty()
           || !newSecret.matches("[a-zA-Z]+")) {
           throw new IllegalArgumentException("Secret must be alphabetic & non‑empty");
       }
       this.secret = newSecret.trim().toLowerCase();
   }


   //─── internals ───────────────────────────────────────────────────────────────


   private void ensureActive() {
       if (isWon() || isLost()) {
           throw new IllegalStateException("Game is already over");
       }
   }
}




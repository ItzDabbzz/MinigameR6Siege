package me.itzdabbzz.siege.builders;


import me.itzdabbzz.siege.Objects.MotherActionBar;

public class ActionBarBuilder {
    private String message;
    private long duration;

    public String getMessage() {
        return message;
    }

    public long getDuration() {
        return duration;
    }

    /**
     * Creates a default, empty ActionBarBuilder.
     */
    public ActionBarBuilder(){
        this("", 20);
    }

    /**
     * Creates an ActionBarBuilder, with selected message.
     *
     * @param message {@link String} that will represent the message.
     */
    public ActionBarBuilder(String message){
        this(message, 20);
    }

    /**
     * Creates an ActionBarBuilder with selected message and duration.
     *
     * @param message {@link String} that will represent the message.
     * @param duration long that will represent the duration.
     */
    public ActionBarBuilder(String message, long duration){
        this.message = message;
        this.duration = duration;
    }

    /**
     * Creates an ActionBarBuilder from custom {@link MotherActionBar}.
     *
     * @param actionBar {@link MotherActionBar} that will be copied.
     */
    public ActionBarBuilder(MotherActionBar actionBar){
        this.message = actionBar.getMessage();
        this.duration = actionBar.getDuration();
    }

    /**
     * Changes the current message.
     *
     * @param message {@link String} that will represent the new message.
     * @return {@link ActionBarBuilder} with changed message.
     */
    public ActionBarBuilder withMessage(String message){
        this.message = message;
        return this;
    }

    /**
     * Replaces a String with another String in the message.
     *
     * @param target {@link String} that should be replaced.
     * @param replacement {@link String} that the target should be replaced with.
     * @return {@link ActionBarBuilder} with changed lore.
     */
    public ActionBarBuilder replaceMessage(String target, String replacement){
        this.message = message.replace(target, replacement);
        return this;
    }

    /**
     * Changes the current duration.
     *
     * @param duration long that will represent the new duration.
     * @return {@link ActionBarBuilder} with changed duration.
     */
    public ActionBarBuilder withDuration(long duration){
        this.duration = duration;
        return this;
    }

    /**
     * Creates {@link MotherActionBar} from selected values.
     *
     * @return {@link MotherActionBar} consisting of all previously selected values.
     */
    public MotherActionBar create(){
        return new MotherActionBar(message, duration);
    }
}

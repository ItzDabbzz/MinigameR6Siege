package me.itzdabbzz.siege.builders;


import me.itzdabbzz.siege.Objects.MotherTitle;

public class TitleBuilder {
    private String title;
    private String subtitle;
    private int in;
    private int stay;
    private int out;

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getIn() {
        return in;
    }

    public int getStay() {
        return stay;
    }

    public int getOut() {
        return out;
    }

    /**
     * Creates a new default, empty TitleBuilder.
     */
    public TitleBuilder(){
        this("", "", 20, 40 ,20);
    }

    /**
     * Creates an TitleBuilder with custom title.
     *
     * @param title {@link String} that will represent the title.
     */
    public TitleBuilder(String title){
        this(title, "", 20, 40, 20);
    }

    /**
     * Creates an TitleBuilder with custom title and subtitle.
     *
     * @param title {@link String} that will represent the tile
     * @param subtitle {@link String} that will represent the subtitle.
     */
    public TitleBuilder(String title, String subtitle){
        this(title, subtitle, 20, 40, 20);
    }

    /**
     * Creates an TitleBuilder with custom title, subtitle and times.
     *
     * @param title {@link String} that will represent the tile
     * @param subtitle {@link String} that will represent the subtitle.
     * @param in int that will represent the time title will take to show up.
     * @param stay int that will represent the time title will be shown after showing up.
     * @param out int that will represent how long the title will take to fully disappear.
     */
    public TitleBuilder(String title, String subtitle, int in, int stay, int out){
        this.title = title;
        this.subtitle = subtitle;
        this.in = in;
        this.stay = stay;
        this.out = out;
    }

    /**
     * Creates an TitleBuilder from {@link MotherTitle}.
     *
     * @param title {@link MotherTitle} that will be copied.
     */
    public TitleBuilder(MotherTitle title){
        this.title = title.getTitle();
        this.subtitle = title.getSubtitle();
        this.in = title.getIn();
        this.stay = title.getStay();
        this.out = title.getOut();
    }

    /**
     * Changes current title.
     *
     * @param title {@link String} that will represent the new title.
     * @return {@link TitleBuilder} with changed title.
     */
    public TitleBuilder withTitle(String title){
        this.title = title;
        return this;
    }

    /**
     * Replaces a String with another String in the title.
     *
     * @param target {@link String} that should be replaced.
     * @param replacement {@link String} that the target should be replaced with.
     * @return {@link TitleBuilder} with changed lore.
     */
    public TitleBuilder replaceTitle(String target, String replacement){
        this.title = title.replace(target, replacement);
        return this;
    }

    /**
     * Changes current subtitle.
     *
     * @param subtitle {@link String} that will represent the new subtitle.
     * @return {@link TitleBuilder} with changed subtitle.
     */
    public TitleBuilder withSubtitle(String subtitle){
        this.subtitle = subtitle;
        return this;
    }

    /**
     * Replaces a String with another String in the subtitle.
     *
     * @param target {@link String} that should be replaced.
     * @param replacement {@link String} that the target should be replaced with.
     * @return {@link TitleBuilder} with changed lore.
     */
    public TitleBuilder replaceSubtitle(String target, String replacement){
        this.subtitle = subtitle.replace(target, replacement);
        return this;
    }

    /**
     * Changes the times of the title.
     *
     * @param in int that will represent the time title will take to show up.
     * @param stay int that will represent the time title will be shown after showing up.
     * @param out int that will represent how long the title will take to fully disappear.
     * @return {@link TitleBuilder} with changed times.
     */
    public TitleBuilder withTimes(int in, int stay, int out){
        this.in = in;
        this.stay = stay;
        this.out = out;
        return this;
    }

    /**
     * Creates a new {@link MotherTitle} from previously defined values.
     *
     * @return {@link MotherTitle} with previously defined values.
     */
    public MotherTitle create(){
        return new MotherTitle(title, subtitle, in, stay, out);
    }
}

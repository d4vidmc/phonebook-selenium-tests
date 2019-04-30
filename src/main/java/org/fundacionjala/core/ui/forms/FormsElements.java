package org.fundacionjala.core.ui.forms;

/** Enum for the org.fundacionjala.core.ui.forms elements. */
public enum FormsElements {
    TITLE("title"),
    DESCRIPTION("description"),
    ACCOUNT("account"),
    TASKENABLE("taskenable"),
    PRIVACY("privacy"),
    NAME("name"),
    STORY_TYPE("storyType"),
    ESTIMATED_POINTS("estimatedPoints"),
    REQUESTER("requester"),
    OWNER("owners"),
    BLOCKERS("blockers");

    private final String element;

    /**
     * Constructor to set element.
     *
     * @param elementName Form element
     */
    FormsElements(final String elementName) {
        this.element = elementName;
    }

    @Override
    public String toString() {
        return this.element;
    }

    /**
     * This method get the name.
     *
     * @return String name.
     */
    public String key() {
        return this.element;
    }
}

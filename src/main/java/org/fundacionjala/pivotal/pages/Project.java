package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.fundacionjala.core.ui.forms.FormsElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this class represented a login page.
 */
@Component
public class Project extends AbstractPage {


    @FindBy(css = ".tc-form__input")
    private WebElement projectNameField;

    @FindBy(css = ".tc-account-selector")
    private WebElement accountSelector;

    @FindBy(css = ".zWDds__Button.pvXpn__Button--positive")
    private WebElement createButton;

    @FindBy(css = ".tc-account-selector__create-account-icon")
    private WebElement createAccount;

    @FindBy(css = ".tc-account-creator__name")
    private WebElement newAccountField;

    @FindBy(linkText = "Delete")
    private WebElement deleteLink;

    @FindBy(css = "#confirm_delete")
    private WebElement confirmDelete;

    @FindBy(css = "#project_name")
    private WebElement editProjectNameField;

    @FindBy(css = "#project_description")
    private WebElement editProjectDescriptionField;

    @FindBy(css = "#project_enable_tasks")
    private WebElement editProjectEnableTask;

    @FindBy(css = "#project_public")
    private WebElement editProjectPrivacy;

    @FindBy(css = "#account_change_link")
    private WebElement editAccountLink;

    @FindBy(css = "#project_account_id_select")
    private WebElement editAccountComboBox;

    @FindBy(xpath = "//input[@name='commit' and @type='submit']")
    private WebElement saveButtonOnEditProject;

    @FindBy(css = "#save_success_bar")
    private WebElement successBar;

    @FindBy(css = ".tc_error_highlight")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[text()='+ Create Project']")
    private WebElement plusNewProjectOption;

    private String projectName;

    @FindBy(css = "a[data-aid='StoryPreviewItem__expander']")
    private List<WebElement> expandStoryButtons;

    /**
     * Clicks the create new project button.
     */

    /**
     * This method set value of project name text field.
     *
     * @param strProjectName value of input.
     */
    public void setProjectNameTextField(final String strProjectName) {
        this.projectName = strProjectName;
        action.setValue(projectNameField, strProjectName);
    }

    /**
     * Open account list.
     **/
    public void openSelectAccountCombobox() {
        action.click(accountSelector);
    }

    /**
     * Select an option from list.
     *
     * @param accountName to select an account from list
     */
    public void selectAccount(final String accountName) {
        openSelectAccountCombobox();
        final boolean onListAccount = action.isExistingSelector(
                By.xpath("//div[text()='" + accountName + "']"));

        if (!onListAccount) {
            action.click(createAccount);
            createAccount(accountName);
        } else {
            final WebElement accountSelectorByName = driver.findElement(
                    By.xpath("//div[text()='" + accountName + "']"));
            action.click(accountSelectorByName);
        }
    }

    /**
     * Create an account by given name.
     *
     * @param accountName name of the new account
     */
    private void createAccount(final String accountName) {
        action.setValue(newAccountField, accountName);
    }

    /**
     * Clicks the create button.
     */
    public void clickCreateButton() {
        action.click(createButton);
        //This is a Workaround.
        action.pause(3000);
        //this need to be reviewed in the last selenium version.
        //action.staleElement(backDrop);
    }

    /**
     * Create new project by given name.
     *
     * @param projectElements value
     */
    public void createNewProject(final Map<String, String> projectElements) {

        final Map<String, ISteps> strategy = new HashMap<>();
        strategy.put(FormsElements.TITLE.toString(),
                () -> setProjectNameTextField(projectElements
                        .get(FormsElements.TITLE.toString())));
        strategy.put(FormsElements.ACCOUNT.toString(),
                () -> selectAccount(projectElements
                        .get(FormsElements.ACCOUNT.toString())));
        strategy.put(FormsElements.PRIVACY.toString(),
                () -> selectProjectPrivacy(projectElements
                        .get(FormsElements.PRIVACY.toString())));

        projectElements.keySet()
                .forEach(key -> strategy.get(key).perform());
        clickCreateButton();
    }

    /**
     * Method to select privacy based on election.
     *
     * @param privacy option public or private
     */
    public void selectProjectPrivacy(final String privacy) {
        final WebElement accountPrivacyOption = driver.findElement(
                By.xpath("//input[@type='radio' and @data-aid='" + privacy + "']"));
        action.click(accountPrivacyOption);
    }

    /**
     * Method to return project's name from context.
     *
     * @return context value on project name
     */
    public String getProjectName() {
        return this.projectName;
    }

    /**
     * Method to keep project's name to context.
     *
     * @param strProjectName project name
     */
    public void setProjectName(final String strProjectName) {
        this.projectName = strProjectName;
    }

    /**
     * Delete through link option.
     */
    public void clickOnDeleteProjectLink() {
        action.scrollToElement(deleteLink);
        action.click(deleteLink);
    }

    /**
     * Cofirm deletion.
     */
    public void clickOnDeleteButton() {
        action.click(confirmDelete);
    }

    /**
     * Set values on form as specified.
     *
     * @param projectElements Attributes to set on form
     */
    public void setValuesOnEditProjectForm(final Map<String, String> projectElements) {
        final Map<String, ISteps> strategy = new HashMap<>();
        strategy.put(FormsElements.TITLE.toString(),
                () -> setEditProjectTitle(projectElements
                        .get(FormsElements.TITLE.toString())));
        strategy.put(FormsElements.DESCRIPTION.toString(),
                () -> setEditProjectDescription(projectElements
                        .get(FormsElements.DESCRIPTION.toString())));
        strategy.put(FormsElements.ACCOUNT.toString(),
                () -> setEditProjectAccount(projectElements
                        .get(FormsElements.ACCOUNT.toString())));
        strategy.put(FormsElements.TASKENABLE.toString(),
                () -> setEditProjectTaskEnable(projectElements
                        .get(FormsElements.TASKENABLE.toString())));
        strategy.put(FormsElements.PRIVACY.toString(),
                () -> setEditProjectPrivacy(projectElements
                        .get(FormsElements.PRIVACY.toString())));

        projectElements.keySet()
                .forEach(key -> strategy.get(key).perform());
    }

    /**
     * change check status if different.
     *
     * @param privacy between private and public
     */
    private void setEditProjectPrivacy(final String privacy) {
        if (editProjectPrivacy.isSelected() && "private".equals(privacy)) {
            action.click(editProjectPrivacy);
        }
    }

    /**
     * change check status if different.
     *
     * @param taskEnable between allow or disallow
     */
    private void setEditProjectTaskEnable(final String taskEnable) {
        if (editProjectPrivacy.isSelected() && "Enable".equals(taskEnable)) {
            action.click(editProjectEnableTask);
        }
    }

    /**
     * Proccess to change account.
     *
     * @param account account name
     */
    private void setEditProjectAccount(final String account) {
        action.click(editAccountLink);
        action.click(editAccountComboBox);
        action.click(By.xpath("//option[contains(text(),'" + account + "')]"));
    }

    /**
     * Set value to description field.
     *
     * @param description the desired value
     */
    private void setEditProjectDescription(final String description) {
        action.setValue(editProjectDescriptionField, description);
    }

    /**
     * Set value to title field.
     *
     * @param title desired title
     */
    private void setEditProjectTitle(final String title) {
        action.setValue(editProjectNameField, title);
    }

    /**
     * Click to save button on edit project form.
     */
    public void saveFormOnEditProject() {
        action.click(saveButtonOnEditProject);
        driver.switchTo().alert().accept();
    }

    /**
     * validate message of success.
     *
     * @return Boolean if message was displayed
     */
    public boolean getResponseMessage() {
        return successBar.isDisplayed();
    }

    /**
     * This method represent expand story action on first story find.
     **/
    public void expandOneStory() {
        if (!this.expandStoryButtons.isEmpty()) {
            this.action.click(this.expandStoryButtons.get(0));
        }
    }

    /**
     * Capture message.
     *
     * @return String with message
     */
    public String getMessageOnNewProjectForm() {
        action.waitVisibility(errorMessage);
        return errorMessage.getText();
    }

    /**
     * Click through plus new project option.
     */
    public void clickCreateNewPRojectOption() {
        action.click(plusNewProjectOption);
    }
}

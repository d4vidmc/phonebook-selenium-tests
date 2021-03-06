package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.fundacionjala.core.ui.forms.FormsElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * This is project setting POM.
 */
@Component
public class ProjectSettings extends AbstractPage {
    @FindBy(css = "#account_change_link")
    private WebElement editAccountLink;

    @FindBy(css = "#project_account_id_select")
    private WebElement editAccountComboBox;
    @FindBy(css = "#project_public")
    private WebElement  editProjectPrivacy;

    @FindBy(css = "#project_name")
    private WebElement editProjectNameField;

    @FindBy(css = "a[id='delete_link']")
    private WebElement deleteLinkProject;

    @FindBy(css = "#project_description")
    private WebElement editProjectDescriptionField;


    @FindBy(css = "#save_success_bar")
    private WebElement successBar;

    @FindBy(css = "#project_enable_tasks")
    private WebElement editProjectEnableTask;

    /**
     * Set value to title field.
     *
     * @param title desired title
     */
    private void setEditProjectTitle(final String title) {
        action.setValue(editProjectNameField, title);
    }
    /**
     * hello.
     */
    public void clickOnDeleteProjectLink() {
        action.scrollToElement(deleteLinkProject);
        action.click(deleteLinkProject);
    }

    /**
     * response message.
     * @return true or false.
     */
    public boolean getResponseMessage() {
        return successBar.isDisplayed();
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
        if (editProjectEnableTask.isSelected() && "Disable".equals(taskEnable)) {
            action.click(editProjectEnableTask);
        }
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
     * Proccess to change account.
     *
     * @param account account name
     */
    private void setEditProjectAccount(final String account) {
        action.click(editAccountLink);
        action.click(editAccountComboBox);
        action.click(By.xpath("//option[contains(text(),'" + account + "')]"));
    }

}

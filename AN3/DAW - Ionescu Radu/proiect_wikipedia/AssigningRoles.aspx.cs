using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class AssigningRoles : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        // Bind the users and roles 
        BindUsersToUserList();
        BindRolesToList();
        // Check the selected user's roles 
        CheckRolesForSelectedUser();
    }
    private void BindUsersToUserList()
    {
        // Get all of the user accounts 
        MembershipUserCollection users = Membership.GetAllUsers();
        UserList.DataSource = users;
        UserList.DataBind();
    }

    private void BindRolesToList()
    {
        // Get all of the roles 
        string[] roles = Roles.GetAllRoles();
        UsersRoleList.DataSource = roles;
        UsersRoleList.DataBind();
    }
    private void CheckRolesForSelectedUser()
    {
        // Determine what roles the selected user belongs to 
        string selectedUserName = UserList.SelectedValue;
        string[] selectedUsersRoles = Roles.GetRolesForUser(selectedUserName);

        // Loop through the Repeater's Items and check or uncheck the checkbox as needed 

        foreach (RepeaterItem ri in UsersRoleList.Items)
        {
            // Programmatically reference the CheckBox 
            CheckBox RoleCheckBox = ri.FindControl("RoleCheckBox") as CheckBox;
            // See if RoleCheckBox.Text is in selectedUsersRoles 
            if (selectedUsersRoles.Contains<string>(RoleCheckBox.Text))
                RoleCheckBox.Checked = true;
            else
                RoleCheckBox.Checked = false;
        }
    }
    protected void UserList_SelectedIndexChanged(object sender, EventArgs e)
    {
        CheckRolesForSelectedUser();
    }
    protected void RoleCheckBox_CheckChanged(object sender, EventArgs e)
    {
        // Reference the CheckBox that raised this event 
        CheckBox RoleCheckBox = sender as CheckBox;

        // Get the currently selected user and role 
        string selectedUserName = UserList.SelectedValue;

        string roleName = RoleCheckBox.Text;

        // Determine if we need to add or remove the user from this role 
        if (RoleCheckBox.Checked)
        {
            // Add the user to the role 
            Roles.AddUserToRole(selectedUserName, roleName);
            // Display a status message 
            ActionStatus.Text = string.Format("User {0} was added to role {1}.", selectedUserName, roleName);
        }
        else
        {
            // Remove the user from the role 
            Roles.RemoveUserFromRole(selectedUserName, roleName);
            // Display a status message 
            ActionStatus.Text = string.Format("User {0} was removed from role {1}.", selectedUserName, roleName);

        }
    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class UpdateProfile : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        if (!Page.IsPostBack)
        {
            TextBox t;
            RadioButtonList r;
            // LVProfile = id-ul din LoginView
            // Functioneaza pe mai multe cazuri: daca exista elementul in pagina, daca esti logat

            t = LVProfile.FindControl("TBFirstName") as TextBox;
            // daca text box-ul este gasit atunci se seteaza valoarea initiala cu cea completata in profil
            if (t != null) t.Text = Profile.FirstName;

            t = LVProfile.FindControl("TBLastName") as TextBox;
            if (t != null) t.Text = Profile.LastName;

            t = LVProfile.FindControl("TBBirthday") as TextBox;
            if (t != null) t.Text = Profile.Birthday.ToShortDateString();

            r = LVProfile.FindControl("RBLGender") as RadioButtonList;
            if (r != null) r.SelectedValue = Profile.Gender.ToString();

          
        }
    }

    protected void SaveButton_Click(object sender, EventArgs e)
    {
        TextBox t;
        RadioButtonList r;
        Literal l;

        l = LVProfile.FindControl("LMessage") as Literal;

        try
        {
            t = LVProfile.FindControl("TBFirstName") as TextBox;
            if (t != null) Profile.FirstName = t.Text;

           

            t = LVProfile.FindControl("TBLastName") as TextBox;
            if (t != null) Profile.LastName = t.Text;

            t = LVProfile.FindControl("TBBirthday") as TextBox;
            if (t != null) Profile.Birthday = DateTime.Parse(t.Text);

            r = LVProfile.FindControl("RBLGender") as RadioButtonList;
            if (r != null) Profile.Gender = int.Parse(r.SelectedValue);

            
            if (l != null) l.Text = "Profile saved!";
        }
        catch (Exception ex)
        {
            if (l != null) l.Text = "Error saving profile: " + ex.Message;
        }
    }

   
}
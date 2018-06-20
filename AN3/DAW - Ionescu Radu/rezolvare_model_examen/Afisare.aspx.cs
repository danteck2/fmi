using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.Configuration;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Afisare : System.Web.UI.Page
{
    
    protected void Page_Load(object sender, EventArgs e)
    {
    }

    protected void search(object sender, EventArgs e)
    {
        SqlDataSource1.SelectParameters["Valoare"].DefaultValue = Valoare.Text;
    }
}
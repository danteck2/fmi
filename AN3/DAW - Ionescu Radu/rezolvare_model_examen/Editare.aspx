<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Editare.aspx.cs" Inherits="Editare" MasterPageFile="MasterPage.master" %>

<asp:Content runat="server" ContentPlaceHolderID="ContentPlaceHolder1">
    <form id="form1" runat="server">
        <h3> Update recenzie </h3>
        <br />
        <br />

         <table>
         <tr>
            <td>Titlul: &nbsp;</td>
            <td><asp:TextBox ID="Titlul" runat="server"></asp:TextBox></td>
            <td>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="Titlul" ErrorMessage="Titlul este obligatoriu"></asp:RequiredFieldValidator>
            </td>
         </tr>

       <tr>
            <td>Comentariu &nbsp;</td>
            <td><asp:TextBox ID="Comentariu" runat="server"></asp:TextBox></td>
            <td>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" ControlToValidate="Comentariu" ErrorMessage="Comentariu este obligatoriu"></asp:RequiredFieldValidator>
            </td>
        </tr>
        <tr>
            <td>Nota: &nbsp;</td>
            <td><asp:TextBox ID="Nota" runat="server"></asp:TextBox></td>
            <td>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator11" runat="server" ControlToValidate="Nota" ErrorMessage="Nota este obligatorie"></asp:RequiredFieldValidator>
                <asp:CompareValidator ID="CompareValidator8" runat="server" ControlToValidate="Nota" ErrorMessage="Tip Int !" Operator="DataTypeCheck" Type="Integer"></asp:CompareValidator>
                <asp:RangeValidator ID="RangeValidator1" ControlToValidate="Nota" runat="server" ErrorMessage="RangeValidator" MaximumValue="5" MinimumValue="1"></asp:RangeValidator>
            </td>
        </tr>

        <tr>
            <td>Data: &nbsp;</td>
            <td><asp:TextBox ID="Data" runat="server"></asp:TextBox></td>
            <td>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" ControlToValidate="Data" ErrorMessage="Data este obligatorie"></asp:RequiredFieldValidator>
                <asp:CompareValidator ID="CompareValidator2" runat="server" ControlToValidate="Data" ErrorMessage="Tip Data !" Operator="DataTypeCheck" Type="Date"></asp:CompareValidator>
            </td>
        </tr>

        <tr>
            <td>Nume Utilizator: &nbsp;</td>
            <td><asp:TextBox ID="NumeUtilizator" runat="server"></asp:TextBox></td>
            <td>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator5" runat="server" ControlToValidate="NumeUtilizator" ErrorMessage="Nume Utilizator este obligatorie"></asp:RequiredFieldValidator>
            </td>
        </tr>
        
       <tr>
            <td>Film: &nbsp;</td>
            <td><asp:TextBox ID="Film" runat="server"></asp:TextBox></td>
            <td>
                <asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" ControlToValidate="Film" ErrorMessage="Film este obligatoriu"></asp:RequiredFieldValidator>
            </td>
        </tr>


        <tr>
            <td>
                <asp:Button ID="Button1" OnClick="editare" runat="server" Text="Update" />
            </td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>

        <tr>
            <td><asp:Literal ID="EroareBazaDate" runat="server"></asp:Literal></td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
        </table>
    </form>
</asp:Content>

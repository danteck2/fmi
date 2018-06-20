<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Afisare.aspx.cs" Inherits="Afisare" MasterPageFile="MasterPage.master" %>

<asp:Content runat="server" ContentPlaceHolderID="ContentPlaceHolder1">

    <form id="form1" runat="server">

        <h3> Afisare dupa nota </h3>
        <br />
        <br />
    
        Introduceti valoare: 
        <asp:TextBox ID="Valoare" runat="server"></asp:TextBox>

        <asp:CompareValidator ID="CompareValidator1" runat="server" ControlToValidate="Valoare" ErrorMessage="Trebuie introdusa o valoare intreaga" Operator="DataTypeCheck" Type="Integer"></asp:CompareValidator>
        <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ErrorMessage="Campul este obligatoriu" ControlToValidate="Valoare"></asp:RequiredFieldValidator>

        <br />
        <asp:Button ID="Button1" runat="server" Text="Cauta" OnClick="search" />
        <br />
        <br />
        <asp:Literal ID="Status" runat="server"></asp:Literal>   
        <br />
        <br />

        <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString='Data Source=(LocalDB)\v11.0;AttachDbFilename="D:\FMI\DOC.AN3\Semestrul1\DAW\Examen\Model Examen 2\10. ModelExamen\App_Data\BazaDeDate3.mdf";Integrated Security=True' SelectCommand="select * from [recenzii] join [film] on (film.id = recenzii.id_film) where nota > @Valoare ORDER BY nota asc">
            <SelectParameters>
                <asp:Parameter Name="Valoare"  DefaultValue="0"/>
            </SelectParameters>
        </asp:SqlDataSource>
        <hr />

        <asp:ListView ID="ListView1" runat="server" DataSourceID="SqlDataSource1">
            <LayoutTemplate>
                <table runat="server" id="table1" >
                    <tr runat="server" id="itemPlaceholder" ></tr>
                </table>
            </LayoutTemplate>
            <ItemTemplate>
                <tr id="Tr1" runat="server">
                    <td style="color: #ac00dc">Titlul: <%#Eval("titlul") %></td>
                    <td style="color: #ac00dc">Comentariu: <%#Eval("comentariu") %></td>
                    <td style="color: #ac00dc">Nota: <%#Eval("nota") %></td>
                    <td style="color: #ac00dc">Data: <%#Eval("data") %></td>
                    <td style="color: #ac00dc">Nume Utilizator: <%#Eval("numeutilizator") %></td>
                    <td style="color: #ac00dc">Film: <%#Eval("denumire") %></td>
                   
                </tr>
                <td>
                    <a href="Editare.aspx?id=<%#Eval("id") %>">Editare</a>
                    <a href="Delete.aspx?id=<%#Eval("id") %>">Stergere</a>
                    </td>
            </ItemTemplate>
        </asp:ListView>

    </form>
</asp:Content>

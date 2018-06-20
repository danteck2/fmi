<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="AssigningRoles.aspx.cs" Inherits="AssigningRoles" %>

<asp:Content ID="Content1" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <h3>Manage Roles By User</h3> 
        <p align="center"> 

            <asp:Label ID="ActionStatus" runat="server" CssClass="Important"></asp:Label> 
        </p>
    <p> 
            <b>Select a User:</b> 
            <asp:DropDownList ID="UserList" runat="server" AutoPostBack="True" 
                DataTextField="UserName" DataValueField="UserName"> 

            </asp:DropDownList> 
    </p> 
    <p> 
            <asp:Repeater ID="UsersRoleList" runat="server"> 
                <ItemTemplate> 
                    <asp:CheckBox runat="server" ID="RoleCheckBox" 
                        AutoPostBack="true" 
                        Text='<%# Container.DataItem %>' 
                        OnCheckedChanged="RoleCheckBox_CheckChanged" />
                    <br /> 
                </ItemTemplate> 
            </asp:Repeater> 
    </p>
</asp:Content>


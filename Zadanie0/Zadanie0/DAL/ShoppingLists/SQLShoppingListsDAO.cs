using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Zadanie0.Models;

namespace Zadanie0.DAL.ShoppingLists
{
    class SQLShoppingListsDAO : IShoppingListsDAO
    {
        private string connectionString;

        public SQLShoppingListsDAO(string connectionString)
        {
            this.connectionString = connectionString;
        }
        public void Delete(int id)
        {
            throw new NotImplementedException();
        }

        public void DeleteAll()
        {
            string queryString = "DELETE FROM ShoppingLists;";

            using (SqlConnection connection = new SqlConnection(
                       connectionString))
            {
                SqlCommand command = new SqlCommand(queryString, connection);
                command.Connection.Open();
                command.ExecuteNonQuery();
            }
        }

        public List<ShoppingListModel> Get()
        {

            string queryString = $"SELECT * FROM ShoppingLists;";
            var shoppingLists = new List<ShoppingListModel>();

            using (SqlConnection connection = new SqlConnection(
                       connectionString))
            {
                SqlCommand command = new SqlCommand(queryString, connection);
                command.Connection.Open();
                var dataReader = command.ExecuteReader();
                while (dataReader.Read())
                {
                    var product = new ShoppingListModel();
                    if (!dataReader.IsDBNull(0))
                        product.Id = dataReader.GetInt32(0);
                    if (!dataReader.IsDBNull(1))
                        product.Name = dataReader.GetString(1);
                    if (!dataReader.IsDBNull(2))
                        product.Comment = dataReader.GetString(2);
                    if (!dataReader.IsDBNull(3))
                        product.Modified = dataReader.GetDateTime(3);

                    shoppingLists.Add(product);
                }
            }
            return shoppingLists;
        }

        public int Save(ShoppingListModel shoppingListDTO)
        {
            string queryString = $@"INSERT INTO ShoppingLists(id, name, comment, modified) VALUES(@id, @name, @comment, @mod)";
                

            int id = 0;

            using (SqlConnection connection = new SqlConnection(
                       connectionString))
            {
                SqlCommand command = new SqlCommand(queryString, connection);
                command.Parameters.AddWithValue("@id", (object)shoppingListDTO.Id ?? DBNull.Value);
                command.Parameters.AddWithValue("@name", (object)shoppingListDTO.Name ?? DBNull.Value);
                command.Parameters.AddWithValue("@comment", (object)shoppingListDTO.Comment ?? DBNull.Value);
                command.Parameters.AddWithValue("@mod", (object)shoppingListDTO.Modified ?? DBNull.Value);
                command.Connection.Open();
                command.ExecuteNonQuery();

            }

            return id;
        }
        }
}

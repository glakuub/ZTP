using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Zadanie0.Models;

namespace Zadanie0.DAL.Products
{
    
    public class SQLProductsDAO : IProductsDAO
    {
        private string connectionString;

        public SQLProductsDAO(string connectionString)
        {
            this.connectionString = connectionString;
        }
        public void DeleteAll()
        {
            string queryString = "TRUNCATE TABLE Products;";
                
                using (SqlConnection connection = new SqlConnection(
                           connectionString))
                {
                    SqlCommand command = new SqlCommand(queryString, connection);
                    command.Connection.Open();
                    command.ExecuteNonQuery();
                }
            
        }

        public List<ProductModel> GetProductsFromList(int id)
        {
            string queryString = $"SELECT * FROM Products WHERE listId={id};";
            var products = new List<ProductModel>();

            using (SqlConnection connection = new SqlConnection(
                       connectionString))
            {
                SqlCommand command = new SqlCommand(queryString, connection);
                command.Connection.Open();
                var dataReader = command.ExecuteReader();
                while(dataReader.Read())
                {
                    var product = new ProductModel();
                    if (!dataReader.IsDBNull(0))
                        product.Id = dataReader.GetInt32(0);
                    if (!dataReader.IsDBNull(1))
                        product.ShoppingListId = dataReader.GetInt32(1);
                    if (!dataReader.IsDBNull(2))
                        product.Name = dataReader.GetString(2);
                    if (!dataReader.IsDBNull(3))
                        product.Quantity = dataReader.GetString(3);
                    if (!dataReader.IsDBNull(4))
                        product.Comment = dataReader.GetString(4);
                    if (!dataReader.IsDBNull(5))
                        product.Modified = dataReader.GetDateTime(5);

                    products.Add(product);
                }
            }
            return products;
        }

        public int Save(ProductModel productDTO)
        {
            string queryString = $@"INSERT INTO Products(id, listId, name, quantity, comment, modified) VALUES(@id, @slid, @name, @qt, @comment, @mod);";

            int id = 0;

            using (SqlConnection connection = new SqlConnection(
                       connectionString))
            {
                SqlCommand command = new SqlCommand(queryString, connection);
                command.Connection.Open();
                command.Parameters.AddWithValue("@id", (object)productDTO.Id ?? DBNull.Value);
                command.Parameters.AddWithValue("@slid", (object)productDTO.ShoppingListId ?? DBNull.Value);
                command.Parameters.AddWithValue("@name", (object)productDTO.Name ?? DBNull.Value);
                command.Parameters.AddWithValue("@qt", (object)productDTO.Quantity ?? DBNull.Value);
                command.Parameters.AddWithValue("@comment", (object)productDTO.Comment ?? DBNull.Value);
                command.Parameters.AddWithValue("@mod", (object)productDTO.Modified ?? DBNull.Value);
                id = command.ExecuteNonQuery();
               
                }
            
            return id;
        }
    }
}

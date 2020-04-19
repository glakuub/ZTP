using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Zadanie0.Models;

namespace Zadanie0.DAL.Products
{
    class FileProductsDAO : IProductsDAO
    {
        private static string FILE_PATH = "Products";
        public List<ProductModel> GetProductsFromList(int id)
        {


            var products = new List<ProductModel>();


            if (File.Exists(FILE_PATH))
            {



                StreamReader file = new StreamReader(FILE_PATH);
                string line;
                while ((line = file.ReadLine()) != null)
                {

                    var data = line.Split(',');
                    if (int.Parse(data[1]) == id)
                    {
                        products.Add(new ProductModel()
                        {
                            Id = int.Parse(data[0]),
                            ShoppingListId = int.Parse(data[1]),
                            Name = data[2],
                            Quantity = data[3],
                            Comment = data[4],
                            Modified = DateTime.Parse(data[5])
                        });
                    }
                }
                file.Close();
            }
            return products;
        }

        public int Save(ProductModel productDTO)
        {

            

            if (productDTO.Id == null)
                productDTO.Id = GetIndex();

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.Append($"{productDTO.Id},");
            stringBuilder.Append($"{productDTO.ShoppingListId},");
            stringBuilder.Append($"{productDTO.Name},");
            stringBuilder.Append($"{productDTO.Quantity},");
            stringBuilder.Append($"{productDTO.Comment},");
            stringBuilder.Append($"{productDTO.Modified}");
            var line = stringBuilder.ToString();
            if (!ContainsLine(line))
            {
                using (StreamWriter file = new StreamWriter(FILE_PATH, true))
                {
                    file.WriteLine(line);
                }
            }

            return productDTO.Id.Value;
        }

        private bool ContainsLine(string line)
        {
            bool result = false;
            if (File.Exists(FILE_PATH))
            {
                StreamReader file = new StreamReader(FILE_PATH);
                string l;
                while ((l = file.ReadLine()) != null)
                {
                    if (l.Equals(line))
                    {
                        result = true;
                        break;
                    }
                }
                file.Close();
            }
            return result;
        }

        private int GetIndex()
        {
            int result = -1;
            if (!File.Exists(FILE_PATH))
            {
                result = 0;
            }
            else
            {
                StreamReader file = new StreamReader(FILE_PATH);
                string line;
                var indecies = new List<int>();
                while ((line = file.ReadLine()) != null)
                {
                    var data = line.Split(',');
                    indecies.Add(int.Parse(data[0]));
                }
                file.Close();
                result = indecies.Count == 0 ? 0 : indecies.Max() + 1;
            }
            return result;

        }

        public void DeleteAll()
        {
            if (File.Exists(FILE_PATH))
                File.Delete(FILE_PATH);
        }

    }
}
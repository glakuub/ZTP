using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Zadanie0.Models;

namespace Zadanie0.DAL.ShoppingLists
{
    class FileShoppingListsDAO : IShoppingListsDAO
    {
        private static string FILE_PATH = "ShoppingLists";

        public void Delete(int id)
        {
            if(File.Exists(FILE_PATH))
            {
                StreamReader file = new StreamReader(FILE_PATH);
                List<string> lines = new List<string>();
                string line;
                while ((line = file.ReadLine()) != null)
                {

                    var data = line.Split(',');
                    if (int.Parse(data[0]) != id)
                    {
                        lines.Add(line);
                    }

                }
                file.Close();
                using(StreamWriter sw = new StreamWriter(FILE_PATH))
                {
                    foreach(var l in lines)
                    {
                        sw.WriteLine(l);
                    }
                }
            }
        }

        public void DeleteAll()
        {
            if (File.Exists(FILE_PATH))
                File.Delete(FILE_PATH);
        }

        public List<ShoppingListModel> Get()
        {
            var lists = new List<ShoppingListModel>();
            if (File.Exists(FILE_PATH))
            {

                StreamReader file = new StreamReader(FILE_PATH);
                string line;
                while ((line = file.ReadLine()) != null)
                {

                    var data = line.Split(',');

                    lists.Add(new ShoppingListModel()
                    {
                        Id = int.Parse(data[0]),
                        Name = data[1],
                        Comment = data[2],
                        Modified = DateTime.Parse(data[3])
                    });

                }
                file.Close();
            }
            
            return lists;
        }

        public int Save(ShoppingListModel shoppingListDTO)
        {
           
            if (shoppingListDTO.Id == null)
                shoppingListDTO.Id = GetIndex();

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.Append($"{shoppingListDTO.Id},");
            stringBuilder.Append($"{shoppingListDTO.Name},");
            stringBuilder.Append($"{shoppingListDTO.Comment},");
            stringBuilder.Append($"{shoppingListDTO.Modified}");
            var line = stringBuilder.ToString();
            if (!ContainsLine(line))
            {
                using (StreamWriter file = new StreamWriter(FILE_PATH, true))
                {
                    file.WriteLine(line);
                }
            }

            return (int)shoppingListDTO.Id;
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


    }
}

package bank.manager.data.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {
   public static final String DB_PATH = "/bank_db/bank_db.txt";
   public static final String DB_HEADER = "_<#>fullName<#>idNumber<#>accountType<#>phoneNumber<#>email<#>balance<#>created";

   public static String dayToday() {
      Format formatter = new SimpleDateFormat("dd-MMM-yy");
      return formatter.format(new Date());
   }
}

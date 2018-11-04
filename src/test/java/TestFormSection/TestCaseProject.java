/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestFormSection;

import java.awt.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 *
 * @author trainee
 */
public class TestCaseProject {
    
    public TestCaseProject() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    Connection conn=null;
    String dbURL = "jdbc:sqlserver://192.168.2.19;databaseName=KovairTraining";
    String TestCaseID=null;
    String TestCaseStatus=null;
    String TestCaseName=null;
    String TesterName="SARMISTHA BHATTACHARYYA";
    String RunID=null;
    ResultSet RID=null;
    WebDriver driver=null;
    String Module="Form-Section";
    String chromepath="C:\\Users\\prasenjitd.IN\\Desktop\\Sarmistha_FormSection\\src\\test\\java\\ChromedriverPackage\\chromedriver.exe";
    String URL="http://10.10.1.26/Kovair8.8/Views/Accounts/Login.aspx";
    List rows=null;

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("------Before Class");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("-----After class");
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("Before Method");
        TestCaseName=null;
        TestCaseID=null;
        TestCaseStatus="Failed";
        try{
            
            driver.findElement(By.id("USERNAME")).clear();
            driver.findElement(By.id("USERNAME")).sendKeys("sb");
            driver.findElement(By.id("PWD")).clear();
            driver.findElement(By.id("PWD")).sendKeys("sb");
            driver.findElement(By.id("Button1")).click();
        }
        catch(Exception e)
        {
            System.out.println("Exception occured.Unable to login"+e);
        }
        try{
            Thread.sleep(7000);
            driver.findElement(By.xpath("//div[@id='div_icon']/img")).click();
            
            Thread.sleep(5000);
            driver.findElement(By.xpath("//li[@id=\"LiWorkspace\"]/a")).click();
            
        }
        catch(Exception e)
        {
            System.out.println("Exception occured.Unable to select workspace"+e);
        }
        try{
            Thread.sleep(3000);
            driver.findElement(By.xpath("//a[contains(@id,'Menu_') and contains(@title,'Entities')]")).click();
            System.out.println("Entities selected");
        }
        catch(Exception e)
        {
            System.out.println("Exception occured.Unable to select Entities"+e);
        }
        try{
            Thread.sleep(5000);
            driver.findElement(By.xpath("//a[contains(@id,'Menu_') and contains(@onclick,'Form-Sections: Entities')]")).click();
            System.out.println("Form section selected");
            Thread.sleep(5000);
        }
        catch(Exception e)
        {
            System.out.println("Exception occured.Unable to select form section"+e);
        }
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        
        System.out.println("After Method");
        Statement st=conn.createStatement();
        
        
        st.executeUpdate("INSERT INTO TestReport VALUES ('"+RunID+"','"+TestCaseID+"','"+TestCaseName+"','"+TestCaseStatus+"','"+Module+"')");
        try{
            driver.switchTo().defaultContent();
            Thread.sleep(5000);
        driver.findElement(By.id("UserImg")).click();
        driver.findElement(By.xpath("//span[contains(@title,'Logout')]")).click();
       
        }
        catch(Exception e)
        {
            System.out.println("Unable to log out");
        }
        
    }
    @BeforeTest
    public void beforeTest() throws Exception
    {
       
        
        System.out.println("Before test"); 
//       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        String user = "kt";
        String pass = "kovair@123";
        
        System.setProperty("webdriver.chrome.driver", chromepath);
            driver=new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(URL);
            Thread.sleep(5000);
        conn = DriverManager.getConnection(dbURL, user, pass);
        try{
           if(conn!=null)
            {
                System.out.println("Connected");
            }
            else
            {
                System.out.println("Not Connected");
            }
            
        }catch(Exception e)
        {
            System.out.println(e.toString());
        }
        
        Statement st=conn.createStatement();
       
        
        st.executeUpdate("INSERT INTO TestReportMaster VALUES (getDate(),'"+TesterName+"')");
        RID=st.executeQuery("SELECT MAX(ID) ID FROM TestReportMaster WHERE Tester ='"+TesterName+"'");
        while(RID.next())
        {
            
            RunID=RID.getString("ID");
            System.out.println("-------"+RunID);
            
       }
       
       //RunID="1";
        
    }
    
    @AfterTest
    public void afterTest() throws Exception {
        System.out.println("After Test");
        //conn.close();
         driver.close();
        
    }
    
    
    @Test(priority=1)
    public void OpenCreateFormSection() throws Exception{
    System.out.println("Test Case 1!!!");
    TestCaseID="Add_Edit_FormSection_001";
    TestCaseName="Verify visiting “Create Form Section”  page.";
    try{
        
        Thread.sleep(5000);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'SectionList.aspx')]")));//"//div[@id='tabHolder']/p[3]/iframe")));
        Thread.sleep(5000);
        System.out.println("Iframe switching");
        driver.findElement(By.xpath("//a[contains(@onclick,'_NewCreateClick(')]")).click();
        Thread.sleep(5000);
       
        System.out.println("Create New Section opened");
        if(driver.findElement(By.id("KovairMaster_Main_lblSectionType")).isDisplayed())
        {
            
        System.out.println("Page Opened sucessfully!!!!!!!");
        TestCaseStatus="Passed";
        }
        else
        {
            System.out.println("Page not opened");
            TestCaseStatus="Failed";
            
        }
    }
    catch(Exception e){
        System.out.println("Unable to Open Create Form Section page with test case id"+TestCaseID+" due to Exception"+e);
    }
    
    }
    
   @Test(priority=2)
   public void MenuListAutoCollapsed() throws Exception{
       System.out.println("TEst Case 2");
       TestCaseID="Add_Edit_FormSection_002";
       TestCaseName="Verify that window(Menu List) is Auto Collapsed when Create Form Section page is opened.";
       try{
        
        Thread.sleep(5000);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'SectionList.aspx')]")));//"//div[@id='tabHolder']/p[3]/iframe")));
        Thread.sleep(5000);
        System.out.println("Iframe switching");
        driver.findElement(By.xpath("//a[contains(@onclick,'_NewCreateClick(')]")).click();
        Thread.sleep(5000);
       
        System.out.println("Create New Section opened");
        Thread.sleep(5000);
        
       }
       catch(Exception e)
       {
            System.out.println("Exception occured.Unable to open Create New Section Page with testcaseid"+TestCaseID+""+e);
        }
      try
       {
             driver.switchTo().defaultContent();
           Thread.sleep(5000);
           if(driver.findElement(By.id("Img1")).isDisplayed())
          {
              TestCaseStatus="Passed";
           }
          else
           {
               TestCaseStatus="Failed";
           }
       }
      catch(Exception e)
       {
           System.out.println("Exception occured in Verifying that window(Menu List) is Auto Collapsed or not with testcaseid "+TestCaseID+""+e);
       }
   }
   
   @Test(priority=3)
   public void CancelButton() throws Exception
   {
       TestCaseID="Add_Edit_FormSection_003";
       TestCaseName="Verify Cancel button in Create Form Section page.";
       System.out.println("Test Case 3");
       try{
        
        Thread.sleep(5000);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'SectionList.aspx')]")));//"//div[@id='tabHolder']/p[3]/iframe")));
        Thread.sleep(5000);
        System.out.println("Iframe switching");
        driver.findElement(By.xpath("//a[contains(@onclick,'_NewCreateClick(')]")).click();
        Thread.sleep(5000);
       
        System.out.println("Create New Section opened");
        Thread.sleep(5000);
        
       }
       catch(Exception e)
       {
            System.out.println("Exception occured.Unable to open Create New Section Page with testcaseid"+TestCaseID+""+e);
        }
      try
       {
           
           Thread.sleep(5000);
           driver.findElement(By.id("KovairMaster_Main_GeneralCancel")).click();
           Thread.sleep(5000);
        if(driver.findElement(By.xpath("//a[contains(@onclick,'_NewCreateClick(')]")).isDisplayed())
        {
            
        
        TestCaseStatus="Passed";
        }
        else
        {
            
            TestCaseStatus="Failed";
            
        }
           
       }
      catch(Exception e)
       {
           System.out.println("Exception occured in canceling the button in create new section page with test id  "+TestCaseID+""+e);
       }
  
       
   }
   
  @Test(priority=4)
   public void Req4() throws Exception
   {
       TestCaseID="Add_Edit_FormSection_004";
       TestCaseName="Verify Save and Finish button in Create Form Section page.";
       String name="Create_New_FormSection";
       //WebElement Item=driver.findElement(By.xpath("//div[contains(@class,'x-grid-cell-inner ')]/span"));
       
       System.out.println("Test Case 4");
       try{
        
        Thread.sleep(5000);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'SectionList.aspx')]")));//"//div[@id='tabHolder']/p[3]/iframe")));
        Thread.sleep(5000);
        System.out.println("Iframe switching");
        driver.findElement(By.xpath("//a[contains(@onclick,'_NewCreateClick(')]")).click();
        Thread.sleep(5000);
       
        System.out.println("Create New Section opened");
        Thread.sleep(5000);
        
       }
       catch(Exception e)
       {
            System.out.println("Exception occured.Unable to open Create New Section Page with testcaseid"+TestCaseID+""+e);
        }
       try{
           
           driver.findElement(By.id("KovairMaster_Main_txtSectionName")).sendKeys(name);
           driver.findElement(By.id("KovairMaster_Main_btnNext")).click();
           Thread.sleep(5000);
           //driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,\"SectionList.aspx\")]")));
           System.out.println("Iframe Selected");
           Thread.sleep(5000);
           
           WebElement to=driver.findElement(By.id("KovairMaster_Main_SectionDesign_section_1"));
           System.out.println("Target selected");
           Thread.sleep(5000);
           WebElement from=driver.findElement(By.xpath("//div[contains(@title,'Activity (System Lookup)')]"));
           System.out.println("Source selected");
           Actions act=new Actions(driver);
           act.dragAndDrop(from, to).build().perform();
           System.out.println("Drag and drop happened");
           Thread.sleep(5000);
           driver.findElement(By.xpath("//input[contains(@value,'Save & Finish') and contains(@id,'KovairMaster_Main_Button1')]")).click();
           Thread.sleep(3000);
   }
       catch(Exception e)
       {
           System.out.println("Exception in drag and droping");
       }
   try{
           Thread.sleep(5000);
            int rows  = driver.findElements(By.xpath("//table[contains(@role,'presentation')]/tbody/tr/td[1]")).size(); 
              
            System.out.println("No of rows are : " + rows);
            String s=null;
            Thread.sleep(5000);
              for(int i=1;i<=rows;i++)
              {
                  s=driver.findElement(By.xpath("//table[contains(@role,'presentation')]/tbody/tr["+i+"]/td[1]")).getText();
                  System.out.println(s);
                  if(s.equals(name))
                  {
                      
                      TestCaseStatus="Passed";
                      break;
                  }
              }
        }
        catch(Exception e)
        {
            System.out.println("Uable to the Edit design of the form section");
        }
       
   } 
   @Test(priority=5)
   public void ViewFormSection() throws Exception
   {
      try{
       System.out.println("TestCase 5");
       TestCaseID="Add_Edit_FormSection_005";
       TestCaseName="Verify Viewing a Form section from Form Section List.";
          driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'SectionList.aspx')]")));//"//div[@id='tabHolder']/p[3]/iframe")));
        Thread.sleep(5000);
        System.out.println("Iframe switching");
       WebElement e= driver.findElement(By.xpath("//span[contains(@onclick,\"_RecordViewClick(5379,\")]"));
       System.out.println("Hello!!!");
       Thread.sleep(5000);
      rightClick(e);
      Thread.sleep(5000);
      driver.findElement(By.id("View-itemEl")).click();
     //Actions act=new Actions(driver);
    // act.doubleClick(e).build().perform();
       Thread.sleep(5000);
       if(driver.findElement(By.id("btnEdit")).isDisplayed())
       {
           TestCaseStatus="Passed";
       }
      }
      catch(Exception e)
      {
          System.out.println("Exception in Verify Viewing a Form section from Form Section List"+e);
      }
       
   }

    public void rightClick(WebElement e) {
     // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    try{
        Actions act=new Actions(driver).contextClick(e);
       act.build().perform();
    }
    catch(Exception ex)
    {
        System.out.println("   "+ex);
    }
    }
    @Test(priority=6)
    public void CancelButtonInViewMode() throws Exception
    {
        
        try{
        TestCaseID="Add_Edit_FormSection_006";
        TestCaseName="Verify the functionality of cancel when Form Section is opened view mode.";
           driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'SectionList.aspx')]")));//"//div[@id='tabHolder']/p[3]/iframe")));
        Thread.sleep(5000);
        System.out.println("Iframe switching");
        }
        catch(Exception e)
        {
            System.out.println("Unable to switch in Iframe");
        }
        try{
       WebElement e= driver.findElement(By.xpath("//span[contains(@onclick,\"_RecordViewClick(5379,\")]"));
       System.out.println("Hello!!!");
       Thread.sleep(5000);
      rightClick(e);
      Thread.sleep(5000);
      driver.findElement(By.id("View-itemEl")).click();
     //Actions act=new Actions(driver);
    // act.doubleClick(e).build().perform();
       Thread.sleep(5000);
        
        }
        catch(Exception e)
        {
            System.out.println("Uable to open the view mode of the form section");
        }
        try{
            driver.findElement(By.xpath("//a[contains(@onclick,\"return ValidateAndSaveForSection('cancel');\") and contains(@id,\"KovairMaster_Main_A\")]")).click();
            Thread.sleep(3000);
            if(driver.findElement(By.xpath("//a[contains(@onclick,'_NewCreateClick(')]")).isDisplayed())
            {
                //System.out.println("Passed!!!!");
                TestCaseStatus="Passed";
            }
            
        }
        catch(Exception e)
        {
            System.out.println("Exception in using cancel button");
        }
        
    }
    
    @Test(priority=7)
    public void VerifyEditFormSection() throws Exception
    {
        String name="New_form_section";
        System.out.println("Test Case 7");
        try{
        TestCaseID="Add_Edit_FormSection_007";
        TestCaseName="Verify Editing Form section from Form Section List.";
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'SectionList.aspx')]")));//"//div[@id='tabHolder']/p[3]/iframe")));
        Thread.sleep(5000);
        System.out.println("Iframe switching");
        }
        catch(Exception e)
        {
            System.out.println("Unable to switch in Iframe");
        }
        try{
        WebElement e= driver.findElement(By.xpath("//span[contains(@onclick,\"_RecordViewClick(5480,\")]"));
       System.out.println("Hello!!!");
       Thread.sleep(5000);
      rightClick(e);
      Thread.sleep(5000);
      driver.findElement(By.id("Edit-itemEl")).click();
     //Actions act=new Actions(driver);
    // act.doubleClick(e).build().perform();
       Thread.sleep(5000);
        
        }
        catch(Exception e)
        {
            System.out.println("Uable to open the Edit mode of the form section");
        }
        try{
            driver.findElement(By.id("KovairMaster_Main_txtSectionName")).clear();
            driver.findElement(By.id("KovairMaster_Main_txtSectionName")).sendKeys(name);
            
            
            Thread.sleep(5000);
            driver.findElement(By.id("KovairMaster_Main_btnNext")).click();
            //driver.findElement(By.xpath("//div[contains(@title,'Associated WBS (System Lookup)')]")).clear();
            WebElement to=driver.findElement(By.id("KovairMaster_Main_SectionDesign_section_2"));
           System.out.println("Target selected");
           Thread.sleep(5000);
           WebElement from=driver.findElement(By.xpath("//div[contains(@title,'Associated WBS (System Lookup)')]"));
           System.out.println("Source selected");
           Actions act=new Actions(driver);
           act.dragAndDrop(from, to).build().perform();
           System.out.println("Drag and drop happened");
            Thread.sleep(5000);
           driver.findElement(By.xpath("//input[contains(@value,'Save & Finish') and contains(@id,'KovairMaster_Main_Button1')]")).click();
           Thread.sleep(3000);
        }
        catch(Exception e)
        {
            System.out.println("Uable to the Edit name of the form section");
        }
        try{
            Thread.sleep(5000);
            int rows  = driver.findElements(By.xpath("//table[contains(@role,'presentation')]/tbody/tr/td[1]")).size(); 
              
            System.out.println("No of rows are : " + rows);
            String s=null;
            Thread.sleep(5000);
              for(int i=1;i<=rows;i++)
              {
                  s=driver.findElement(By.xpath("//table[contains(@role,'presentation')]/tbody/tr["+i+"]/td[1]")).getText();
                  System.out.println(s);
                  if(s.equals(name))
                  {
                      
                      TestCaseStatus="Passed";
                      break;
                  }
              }
        }
        catch(Exception e)
        {
            System.out.println("Uable to the Edit design of the form section");
        }
        
        
    }
    
    @Test(priority=8)
    public void CancelButtonInEditMode() throws Exception
    {
        int t=0;
        TestCaseID="Add_Edit_FormSection_008";
        TestCaseName="Verify the functionality of cancel when Form Section is opened in Edit mode from Form Section list page.";
        String name="New_form_section 1";
        System.out.println("Test Case 8");
        try{
        
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'SectionList.aspx')]")));//"//div[@id='tabHolder']/p[3]/iframe")));
        Thread.sleep(5000);
        System.out.println("Iframe switching");
        }
        catch(Exception e)
        {
            System.out.println("Unable to switch in Iframe");
        }
        try{
        WebElement e= driver.findElement(By.xpath("//span[contains(@onclick,\"_RecordViewClick(5480,\")]"));
       System.out.println("Hello!!!");
       Thread.sleep(5000);
      rightClick(e);
      Thread.sleep(5000);
      driver.findElement(By.id("Edit-itemEl")).click();
     //Actions act=new Actions(driver);
    // act.doubleClick(e).build().perform();
       Thread.sleep(5000);
        
        }
        catch(Exception e)
        {
            System.out.println("Uable to open the Edit mode of the form section");
        }
        try{
            driver.findElement(By.id("KovairMaster_Main_txtSectionName")).clear();
            driver.findElement(By.id("KovairMaster_Main_txtSectionName")).sendKeys(name);
            
            
            Thread.sleep(5000);
            driver.findElement(By.id("KovairMaster_Main_btnNext")).click();
            //driver.findElement(By.xpath("//div[contains(@title,'Associated WBS (System Lookup)')]")).clear();
            WebElement to=driver.findElement(By.id("KovairMaster_Main_SectionDesign_section_2"));
           System.out.println("Target selected");
           Thread.sleep(5000);
           WebElement from=driver.findElement(By.xpath("//div[contains(@title,'Associated WBS (System Lookup)')]"));
           System.out.println("Source selected");
           Actions act=new Actions(driver);
           act.dragAndDrop(from, to).build().perform();
           System.out.println("Drag and drop happened");
            Thread.sleep(5000);
           driver.findElement(By.xpath("//a[contains(@id,'KovairMaster_Main_DetailCancel') and contains(@class,'hyperlinkNoLine')]")).click();
           Thread.sleep(3000);
        }
        catch(Exception e)
        {
            System.out.println("Uable to the Edit name of the form section"+e);
        }
       try
       {
           if(driver.findElement(By.xpath("//a[contains(@onclick,\"_NewCreateClick(\")]")).isDisplayed())
           {
              int rows  = driver.findElements(By.xpath("//table[contains(@role,'presentation')]/tbody/tr/td[1]")).size(); 
              
            System.out.println("No of rows are : " + rows);
            String s=null;
            Thread.sleep(5000);
              for(int i=1;i<=rows;i++)
              {
                  s=driver.findElement(By.xpath("//table[contains(@role,'presentation')]/tbody/tr["+i+"]/td[1]")).getText();
                  System.out.println(s);
                  if(s.equals(name))
                  {
                      //System.out.println("GOT IT");
                      t=1;
                      //TestCaseStatus="Passed";
                      break;
                  }
                  
              } 
              if(t==0)
              {
                  TestCaseStatus="Passed";
              }
           }
       }
       catch(Exception e)
       {
           System.out.println("Exception occured in finding form section");
       }
        
        
    }
    
    @Test(priority=9)
    public void VerifyEditFormsectionInViewMode() throws Exception
    {
        TestCaseID="Add_Edit_FormSection_009";
        TestCaseName="Verify Editing Form section while a Form Section is opened in view mode.";
        System.out.println("Test Case 9");
        String name="New_form_section";
        try{
        
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'SectionList.aspx')]")));//"//div[@id='tabHolder']/p[3]/iframe")));
        Thread.sleep(5000);
        System.out.println("Iframe switching");
        }
        catch(Exception e)
        {
            System.out.println("Unable to switch in Iframe");
        }
        try{
        WebElement e= driver.findElement(By.xpath("//span[contains(@onclick,\"_RecordViewClick(5480,\")]"));
       System.out.println("Hello!!!");
       Thread.sleep(5000);
      rightClick(e);
      Thread.sleep(5000);
      driver.findElement(By.id("View")).click();
      
     //Actions act=new Actions(driver);
    // act.doubleClick(e).build().perform();
       Thread.sleep(5000);
        
        }
        catch(Exception e)
        {
            System.out.println("Uable to open the view mode of the form section");
        }
       
        try
        {
            driver.findElement(By.id("btnEdit")).click();
            Thread.sleep(5000);
            if(driver.findElement(By.id("KovairMaster_Main_btnNext")).isDisplayed())
            {
                TestCaseStatus="Passed";
            }
        }
        catch(Exception e)
        {
            System.out.println("Exception in editing");
        }
    }
    
    @Test(priority=10)
    public void Req10()throws Exception{
        int t=0;
        TestCaseID="Add_Edit_FormSection_010";
        TestCaseName="Verify the functionality of cancel when Form Section is opened in Edit mode from Form Section View page.";
        System.out.println("Test Case 10");
        String name="New_form_section1";
        try{
        
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'SectionList.aspx')]")));//"//div[@id='tabHolder']/p[3]/iframe")));
        Thread.sleep(5000);
        System.out.println("Iframe switching");
        }
        catch(Exception e)
        {
            System.out.println("Unable to switch in Iframe");
        }
        try{
        WebElement e= driver.findElement(By.xpath("//span[contains(@onclick,\"_RecordViewClick(5480,\")]"));
       System.out.println("Hello!!!");
       Thread.sleep(5000);
      rightClick(e);
      Thread.sleep(5000);
      driver.findElement(By.id("View")).click();
      
     //Actions act=new Actions(driver);
    // act.doubleClick(e).build().perform();
       Thread.sleep(5000);
        
        }
        catch(Exception e)
        {
            System.out.println("Uable to open the view mode of the form section");
        }
       
        try
        {
            driver.findElement(By.id("btnEdit")).click();
            Thread.sleep(5000);
            
        }
        catch(Exception e)
        {
            System.out.println("Exception in editing the form");
        }
        try{
            driver.findElement(By.id("KovairMaster_Main_txtSectionName")).clear();
            driver.findElement(By.id("KovairMaster_Main_txtSectionName")).sendKeys(name);
            driver.findElement(By.id("KovairMaster_Main_btnNext")).click();
              WebElement to=driver.findElement(By.id("KovairMaster_Main_SectionDesign_section_3"));
           System.out.println("Target selected");
           Thread.sleep(5000);
           WebElement from=driver.findElement(By.id("KovairMaster_Main_SectionDesign_Field_9_Field"));
           System.out.println("Source selected");
           Actions act=new Actions(driver);
           act.dragAndDrop(from, to).build().perform();
           System.out.println("Drag and drop happened");
            Thread.sleep(5000);
           driver.findElement(By.id("KovairMaster_Main_DetailCancel")).click();
           Thread.sleep(3000);
        }
        catch(Exception e)
        {
            System.out.println("Uable to the Edit name of the form section");
        }
        try{
            if(driver.findElement(By.id("btnEdit")).isDisplayed())
            {
                WebElement e=driver.findElement(By.id("KovairMaster_Main_txtSectionNameView"));
                String s=e.getText().trim();
                if(s==name)
                {
                    TestCaseStatus="Failed";
                    
                }
                else
                    TestCaseStatus="Passed";
            }
            else
                System.out.println("Unable to open vieww page");
        }
        
        catch(Exception e)
        {
            System.out.println("Exception in cancel button");
        }
            
        }
    }



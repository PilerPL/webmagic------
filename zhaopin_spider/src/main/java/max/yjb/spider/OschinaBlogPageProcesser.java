package max.yjb.spider;


import max.yjb.dao.LaGouDao;
import max.yjb.entity.LaGouInfo;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class OschinaBlogPageProcesser implements PageProcessor {
	//private int  a = 2;
    private Site site = Site.me().setRetryTimes(3).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36");
    public Site getSite() {
        return site;

    }

    
    public void process(Page page) {
    	if(page.getUrl().toString().contains("filterOption")) {
			for(int i = 1; i<16; i++) {
				page.addTargetRequest(page.getHtml().xpath("ul[@class='item_con_list']/li["+i+"]/div/div/div/a/@href").toString());
			}
			/*if(a<101) {
				System.out.println("****************************************** "+(a-1)+" ****************************************************");
				page.addTargetRequest("https://www.lagou.com/zhaopin/Java/"+a+"/?filterOption="+a);
				a++;
			}*/
//			page.putField("url", page.getHtml().xpath("ul[@class='sojob-list']/li[1]/div/div[1]/h3/a/@href"));
//			page.putField("param", page.getHtml().xpath("ul[@class='sojob-list']/li[1]/div/div[1]/h3/a/@data-promid"));
		}else{
    	LaGouInfo lg = new LaGouInfo();
    	LaGouDao ld = new LaGouDao();
    	lg.setWebName(page.getHtml().xpath("h1/text()").toString());
    	lg.setCompany(page.getHtml().xpath("div[@class='company']/allText()").toString());
    	lg.setSalary(page.getHtml().xpath("span[@class='ceil-salary']/text()").toString());
    	lg.setAdvantage(page.getHtml().xpath("dd[@class='job-advantage']/p/allText()").toString());
    	lg.setDescription(page.getHtml().xpath("dd[@class='job_bt']/div/allText()").toString());
    	lg.setAddr(page.getHtml().xpath("dd[@class='job_bt']/div/allText()").toString());
    	ld.save(lg);
		}
    	/*page.putField("网站名称", page.getHtml().xpath("h1/text()").toString());
    	page.putField("公司名称", page.getHtml().xpath("div[@class='company']/allText()").toString());
    	page.putField("salary", page.getHtml().xpath("span[@class='ceil-salary']/text()").toString());
    	page.putField("职业诱惑", page.getHtml().xpath("dd[@class='job-advantage']/p/allText()").toString());
    	page.putField("职位描述", page.getHtml().xpath("dd[@class='job_bt']/div/allText()").toString());
    	page.putField("工作地址", page.getHtml().xpath("div[@class='work_addr']/allText()").toString());
   */
    	}

   
    public static void main(String[] args) {
        Spider.create(new OschinaBlogPageProcesser()).addUrl("https://www.lagou.com/zhaopin/Java/1/?filterOption=1")
             .addPipeline(new ConsolePipeline()).run();
        
    }
}
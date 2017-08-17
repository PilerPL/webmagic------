package max.yjb.spider;

import max.yjb.dao.ZhiLianDao;
import max.yjb.entity.ZhiLianInfo;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class ZhiLian  implements PageProcessor{
    private Site site = Site.me().setRetryTimes(3).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.109 Safari/537.36");
    public Site getSite() {
        return site;

    }
private int a =6;
    
    public void process(Page page) {
    	if(page.getUrl().toString().contains("p=")) {
			for(int i = 2; i<52; i++) {			 
				page.addTargetRequest(page.getHtml().xpath("div[@class='newlist_list_content']/table["+i+"]/tbody/tr/td/div/a/@href").toString());
			}
			/*if(a<91) {
				System.out.println("****************************************** "+a+" ****************************************************");
				page.addTargetRequest("http://sou.zhaopin.com/jobs/searchresult.ashx?bj=160000&sj=079%3b2040&in=210500%3b160400&jl=%E5%8C%97%E4%BA%AC&kw=java&sm=0&sg=a5eb91ecad2b4533b69c157e66d35ab7&p="+a);
				a++;
			}*/
    	}else{
    		ZhiLianInfo zl = new ZhiLianInfo();
    		zl.setJobName(page.getHtml().xpath("h1/text()").toString());
    		zl.setCompanyName(page.getHtml().xpath("h2/a/text()").toString());
    		zl.setEntice(page.getHtml().xpath("div[@class='terminalpage-left']/ul/allText()").toString());
    		zl.setJobDesc(page.getHtml().xpath("div[@class='terminalpage-main clearfix']/div[@class='tab-cont-box']/div[@class='tab-inner-cont']/allText()").toString());
    		zl.setComDesc(page.getHtml().xpath("div[@class='terminalpage-main clearfix']/div[@class='tab-cont-box']/div[@class='tab-inner-cont']/div/allText()").toString());
    		ZhiLianDao zd = new ZhiLianDao();
    		zd.save(zl);
//    	page.putField("岗位名称", page.getHtml().xpath("h1/text()").toString());
//    	page.putField("公司名称", page.getHtml().xpath("h2/a/text()").toString());
//   		page.putField("诱惑", page.getHtml().xpath("div[@class='terminalpage-left']/ul/allText()").toString());
//       	page.putField("职位描述", page.getHtml().xpath("div[@class='terminalpage-main clearfix']/div[@class='tab-cont-box']/div[@class='tab-inner-cont']/allText()").toString());
//       	page.putField("公司介绍", page.getHtml().xpath("div[@class='terminalpage-main clearfix']/div[@class='tab-cont-box']/div[@class='tab-inner-cont']/div/allText()").toString());
    	}
    	
    	}

   
    public static void main(String[] args) {
    	for (int i = 7; i < 91; i++) {
    		System.out.println("****************************************** "+i+" ****************************************************");
    		Spider.create(new ZhiLian()).addUrl("http://sou.zhaopin.com/jobs/searchresult.ashx?bj=160000&sj=079%3b2040&in=210500%3b160400&jl=%E5%8C%97%E4%BA%AC&kw=java&sm=0&sg=a5eb91ecad2b4533b69c157e66d35ab7&p="+i)
    		.addPipeline(new ConsolePipeline()).thread(5).run();			
		}
        
    }
}

package ca.esystem.bridges.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import ca.esystem.bridges.domain.Address;
import ca.esystem.bridges.domain.Blog;
import ca.esystem.bridges.domain.Business_Profile;
import ca.esystem.bridges.domain.Contact;
import ca.esystem.bridges.domain.CustomerRepInfo;
import ca.esystem.bridges.domain.Media;
import ca.esystem.bridges.domain.Membership;
import ca.esystem.bridges.domain.Search_Missed;
import ca.esystem.bridges.domain.Ticket;
import ca.esystem.bridges.domain.Ticket_Reply;
import ca.esystem.bridges.domain.User_History;

/**
 * create Entities assertEquals method
 * 
 * @author cherie
 * 
 */
public class EntitiesHelper {
    private static Ticket  baseTicket  = new Ticket();

    private static Contact baseContact = new Contact();

    /**
     * assert method for Ticket by cherie
     * 
     * @param a
     * @param e
     */
    protected static void assertTicketEquals(Ticket a, Ticket e) {
        setBaseTicket(baseTicket);
        assertNotNull(e);
        assertEquals(a.getTitle(), e.getTitle());
        assertEquals(a.getDescription(), e.getDescription());
        assertEquals(a.getStatus_code(), e.getStatus_code());
        assertEquals(a.getType_code(), e.getType_code());
        assertEquals(a.getSource_id(), e.getSource_id());
        assertEquals(a.getCreated_by(), e.getCreated_by());
        /*
         * assertEquals(a.getModified_by(), e.getModified_by()); assertEquals(a.getClose_by(), e.getClose_by());
         */

    }

    public static void assertContactEquals(Contact a, Contact e) {
        assertNotNull(e);

        assertEquals(e.getId(), a.getId());
        assertEquals(e.getType_code(), a.getType_code());
        assertEquals(e.getContact_value(), a.getContact_value());
        assertEquals(e.getUser_id(), a.getUser_id());
    }

    public static void assertTicket(Ticket a) {

        assertTicketEquals(a, baseTicket);
    }

    /**
     * set ticket basedata method for TicketDaoTest by cherie
     * 
     * @param t
     */
    public static void setBaseTicket(Ticket t) {
        t.setTitle("测试用例题目1");
        t.setDescription("测试描述具体111111111111");
        t.setStatus_code("10");
        t.setType_code("PHONE");
        t.setSource_id(11);
        t.setCreated_by(1);
        t.setModified_by(2);
        t.setClose_by(2);
    }

    /**
     * assert method for Ticket_ReplyDaoTest by cherie
     * 
     * @param a
     * @param e
     */
    protected static void assertTicketReplyEquals(Ticket_Reply a, Ticket_Reply e) {
        assertNotNull(a);
        assertNotNull(e);
        assertEquals(a.getReply_msg(), e.getReply_msg());
        assertEquals(a.getReply_by(), e.getReply_by());
        assertEquals(a.getReplier_name(), e.getReplier_name());
    }

    /**
     * assert method for Search_MissedDaoTest by cherie
     * 
     * @param a
     * @param e
     */
    protected static void assertSearchMissedEquals(Search_Missed a, Search_Missed e) {
        assertNotNull(a);
        assertNotNull(e);
        assertEquals(a.getKeyword(), e.getKeyword());
        assertEquals(a.getUser_id(), e.getUser_id());
        assertEquals(a.getUser_ip_address(), e.getUser_ip_address());

    }

    /**
     * assert method for BlogDaoTest by cherie
     * 
     * @param a
     * @param e
     */
    protected static void assertBlogEquals(Blog a, Blog e) {
        assertNotNull(a);
        assertNotNull(e);
        assertEquals(a.getTitle(), e.getTitle());
        assertEquals(a.getContent(), e.getContent());
        assertEquals(a.getMeta_keyword(), e.getMeta_keyword());
        assertEquals(a.getMeta_dec(), e.getMeta_dec());
        assertEquals(a.getBlog_status(), e.getBlog_status());
        assertEquals(a.getClass_id(), e.getClass_id());
    }
    /**
     * assert method for MediaDaoTest by cherie
     * 
     * @param a
     * @param e
     */
    protected static void assertMediaEquals(Media a, Media e)
    {
        assertNotNull(a);
        assertNotNull(e);
        assertEquals(a.getTitle(), e.getTitle());
        assertEquals(a.getThumb(),e.getThumb());
        assertEquals(a.getDescription(),e.getDescription());
        assertEquals(a.getBigpic(),e.getBigpic());
        assertEquals(a.getExt_name(),e.getExt_name());
    }
    
    /**
     * assert method for AddressDaoTest by cherie
     * 
     * @param a
     * @param e
     */
    protected static void assertAddressEquals(Address a, Address e)
    {
        assertNotNull(a);
        assertNotNull(e);
        assertEquals(a.getAddress(),e.getAddress());
        assertEquals(a.getDistrict(),e.getDistrict());
        assertEquals(a.getCity_code(),e.getCity_code());
        assertEquals(a.getPostal_code(),e.getPostal_code());
        assertEquals(a.getUser_id(),e.getUser_id());
        assertEquals(a.getCreated_by(),e.getCreated_by());
    
    }
    
    /**
     * assert method for MemmberDaoTest by cherie
     * 
     * @param a
     * @param e
     */
    protected static void assertMembershipEquals(Membership a, Membership e)
    {
        assertNotNull(a);
        assertNotNull(e);
        assertEquals(a.getUser_id(),e.getUser_id());
        assertEquals(a.getType_code(),e.getType_code());
        assertEquals(a.getCredit(),e.getCredit());
        assertEquals(a.getCreated_by(),e.getCreated_by());
        assertEquals(a.getModified_by(),e.getModified_by());
    }
    
    /**
     * assert method for MemmberDaoTest by cherie
     * 
     * @param a
     * @param e
     */
    protected static void assertBusinessProfileEquals(Business_Profile a, Business_Profile e)
    {
        assertNotNull(a);
        assertNotNull(e);
        assertEquals(a.getBusiness_number(),e.getBusiness_number());
        assertEquals(a.getBusiness_name(),e.getBusiness_name());
        assertEquals(a.getRecommend_level_id(),e.getRecommend_level_id());
        
    }
    
    /**
     * assert method for UserHistoryDaoTest by cherie
     * 
     * @param a
     * @param e
     */
    protected static void assertUserHistoryEquals(User_History a, User_History e)
    {
        assertNotNull(a);
        assertNotNull(e);
        assertEquals(a.getUser_id(),e.getUser_id());
        assertEquals(a.getContent(),e.getContent());
        assertEquals(a.getCreated_by(),e.getCreated_by());
        assertEquals(a.getCreater_name(),e.getCreater_name());
        
        
    }
    
    /**
     * assert method for CustomerRepDaoTest by cherie
     * 
     * @param a
     * @param e
     */

    protected static void assertCustomerRepInfoEquals(CustomerRepInfo a, CustomerRepInfo e) {
        // TODO Auto-generated method stub
        assertNotNull(a);
        assertNotNull(e);
        assertEquals(a.getUser_id(),e.getUser_id());
        assertEquals(a.getMember_id(),e.getMember_id());
        assertEquals(a.getPhone(),e.getPhone());
        assertEquals(a.getEmail(),e.getEmail());
        assertEquals(a.getFirstName(),e.getFirstName());
        assertEquals(a.getLastName(),e.getLastName());
        assertEquals(a.getType_code(),e.getType_code());
        assertEquals(a.getStatus(),e.getStatus());
        assertEquals(a.getPreferred_language(),e.getPreferred_language());
        
        
    }

}

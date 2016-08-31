/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspigrow.jerseyapp;

/**
 *
 * @author Elcot
 */
public interface Constants {

    String ORDER = "Name,Place,Location,Country,Name,Supplier’s Legal Name";
    
    String DOC_HEADING = "NONDISCLOSURE AGREEMENT";
    
    String DOC_HEADER_LINE = "%s with a place of business at %s located at %s, %s enter into this agreement with the intent to exchange certain information with each other, "
            + "and in consideration for said exchange agree as follows:";

    String FIRST_POINT = "1.	Each party (the \"receiving party\") agrees that it shall use any proprietary information disclosed by the other party "
            + "(the \"disclosing party\") under this Agreement only for the purpose of discussing and evaluating a potential business relationship between "
            + "the parties and will not use any such proprietary information for the provision of services or products. ";

    String SECOND_POINT = "2.	The receiving party shall not disclose any proprietary information disclosed to it by the disclosing party to anyone other "
            + "than its or its affiliates, employees, contractors or authorized representatives of the receiving party who have a need to know the information "
            + "in connection with the purpose described in paragraph 1 and who have signed confidentiality agreements or are otherwise bound by confidentiality "
            + "obligations at least as restrictive as those contained herein.  The receiving party shall exercise the same degree of care to prevent disclosure "
            + "of any proprietary information received from the disclosing party hereunder as it takes to preserve and safeguard its own confidential information but,"
            + " in any event, no less than a reasonable degree of care.  In the event of any loss or improper disclosure of the proprietary information, the receiving "
            + "party shall promptly notify the disclosing party.";

    String THIRD_POINT = "3.	The existence, terms and conditions of this Agreement are confidential and shall not be disclosed by the Parties to any third-party "
            + "without the other party’s prior written consent.  The obligations of the receiving party with respect to the proprietary information contained in this "
            + "Agreement shall, unless specifically released earlier by the disclosing party in writing, extend for a period of three (3) years from the date on which "
            + "such proprietary information is disclosed. ";

    String FOURTH_POINT = "4.	This Agreement shall terminate one (1) year after the effective date of this Agreement, except for the obligations of the parties hereto "
            + "with respect to proprietary information received prior to such termination which shall survive such termination pursuant to paragraph 3 above.";

    String FIFTH_POINT = "5.	No rights or obligations other than those expressly provided for in this Agreement shall be implied from this Agreement.  Nothing herein "
            + "contained shall in any way affect the present and prospective rights of the parties under the patent laws of any country, or be construed to (i) grant to "
            + "the receiving party a license under any present or future patent, patent application, trade secret or trademark related to the proprietary information of "
            + "the disclosing party or (ii) restrict in any way the marketing of any product or merchandise of the receiving party unless such marketing will constitute a "
            + "breach of this Agreement by the receiving party.";

    String SIXTH_POINT = "6.	This Agreement shall be binding upon and inure to the benefit of the parties hereto and their respective successors, assigns and legal "
            + "representatives.  Neither party shall have the right to assign or otherwise transfer its rights or obligations under this Agreement except with the prior "
            + "written consent of the other party, not to be unreasonably withheld.  ";

    String SEVENTH_POINT = "7.	IN THE EVENT OF ANY DISPUTE BETWEEN THE PARTIES, WHETHER IT RESULTS IN PROCEEDINGS IN ANY COURT IN ANY JURISDICTION OR IN ARBITRATION, "
            + "THE PARTIES HEREBY KNOWINGLY AND VOLUNTARILY, AND HAVING HAD AN OPPORTUNITY TO CONSULT WITH COUNSEL, WAIVE ALL RIGHTS TO TRIAL BY JURY, AND AGREE THAT "
            + "ANY AND ALL MATTERS SHALL BE DECIDED BY A JUDGE OR ARBITRATOR WITHOUT A JURY TO THE FULLEST EXTENT PERMISSIBLE UNDER APPLICABLE LAW.";

    String EIGHTH_POINT = "8.	In this Agreement, the term \"proprietary information\" means any information disclosed for the evaluation or discussion contemplated hereby,"
            + " that the disclosing party owns or otherwise controls, except information which:";

    String EIGHTH_POINT_A = "a.	is already known to, or independently developed by, the receiving party;";

    String EIGHTH_POINT_B = "b.	is already publicly available or becomes publicly available without a breach of this Agreement by the receiving party;";

    String EIGHTH_POINT_C = "c.	is lawfully received by the receiving party from a third party;";

    String EIGHTH_POINT_D = "d.	is not either (i) disclosed in writing and identified thereon as confidential or proprietary, or (ii) if first disclosed orally, "
            + "identified as confidential or proprietary at the time of oral disclosure and so confirmed in writing within thirty (30) days after such oral disclosure, "
            + "except that information disclosed under this Agreement which would reasonably be expected, by the receiving party, to be considered confidential in the "
            + "context in which such information is disclosed shall be considered proprietary information; ";

    String EIGHTH_POINT_E = "e.	becomes known to the receiving party by examining a product or merchandise made publicly available by the disclosing party; or";

    String EIGHTH_POINT_F = "f.	is required to be disclosed by law or a valid order by a court or other governmental body, provided that the receiving party provides "
            + "the disclosing party with prior written notice of such disclosure in order to permit the disclosing party to seek confidential treatment of such information."
            + "Notwithstanding the foregoing, the receiving party shall treat as proprietary information under this Agreement, any information which would reasonably be "
            + "expected, by the receiving party, to be considered confidential in the context in which such information is disclosed.";

    String NINTH_POINT = "9.	Upon written request of the disclosing party, the receiving party shall promptly return to the disclosing party all the proprietary "
            + "information disclosed by the disclosing party.  Upon termination of this Agreement, unless requested otherwise in writing by the disclosing party, "
            + "the receiving party shall return to the disclosing party or destroy and provide the disclosing party with notice of such destruction, all proprietary "
            + "information disclosed by the disclosing party including all copies.";

    String TENTH_POINT = "10.	This Agreement contains the final, complete and exclusive agreement of the parties relative to the subject matter hereof, and supersedes"
            + " all prior and contemporaneous understanding and agreements relating thereto. The parties hereto, intending to be legally bound hereby, have caused this "
            + "Agreement to be duly executed as of the later date written below.";

    String FOOTER_LINE_1 = "Contractforce %s            %s";
    String FOOTER_LINE_2 = "Signed By: %s";
    String FOOTER_LINE_3 = "Title: ";
    String FOOTER_LINE_4 = "Date: ";
    
    String DOC_TEXT = "NONDISCLOSURE AGREEMENT"
            + "%s with a place of business at %s located at %s,%s enter into this agreement with the intent to exchange certain information with each other, and in consideration for said exchange agree as follows:"
            + "1.	Each party (the \"receiving party\") agrees that it shall use any proprietary information disclosed by the other party (the \"disclosing party\") under this Agreement only for the purpose of discussing and evaluating a potential business relationship between the parties and will not use any such proprietary information for the provision of services or products. "
            + "2.	The receiving party shall not disclose any proprietary information disclosed to it by the disclosing party to anyone other than its or its affiliates, employees, contractors or authorized representatives of the receiving party who have a need to know the information in connection with the purpose described in paragraph 1 and who have signed confidentiality agreements or are otherwise bound by confidentiality obligations at least as restrictive as those contained herein.  The receiving party shall exercise the same degree of care to prevent disclosure of any proprietary information received from the disclosing party hereunder as it takes to preserve and safeguard its own confidential information but, in any event, no less than a reasonable degree of care.  In the event of any loss or improper disclosure of the proprietary information, the receiving party shall promptly notify the disclosing party."
            + "3.	The existence, terms and conditions of this Agreement are confidential and shall not be disclosed by the Parties to any third-party without the other party’s prior written consent.  The obligations of the receiving party with respect to the proprietary information contained in this Agreement shall, unless specifically released earlier by the disclosing party in writing, extend for a period of three (3) years from the date on which such proprietary information is disclosed. "
            + "4.	This Agreement shall terminate one (1) year after the effective date of this Agreement, except for the obligations of the parties hereto with respect to proprietary information received prior to such termination which shall survive such termination pursuant to paragraph 3 above."
            + "5.	No rights or obligations other than those expressly provided for in this Agreement shall be implied from this Agreement.  Nothing herein contained shall in any way affect the present and prospective rights of the parties under the patent laws of any country, or be construed to (i) grant to the receiving party a license under any present or future patent, patent application, trade secret or trademark related to the proprietary information of the disclosing party or (ii) restrict in any way the marketing of any product or merchandise of the receiving party unless such marketing will constitute a breach of this Agreement by the receiving party."
            + "6.	This Agreement shall be binding upon and inure to the benefit of the parties hereto and their respective successors, assigns and legal representatives.  Neither party shall have the right to assign or otherwise transfer its rights or obligations under this Agreement except with the prior written consent of the other party, not to be unreasonably withheld.  "
            + "7.	IN THE EVENT OF ANY DISPUTE BETWEEN THE PARTIES, WHETHER IT RESULTS IN PROCEEDINGS IN ANY COURT IN ANY JURISDICTION OR IN ARBITRATION, THE PARTIES HEREBY KNOWINGLY AND VOLUNTARILY, AND HAVING HAD AN OPPORTUNITY TO CONSULT WITH COUNSEL, WAIVE ALL RIGHTS TO TRIAL BY JURY, AND AGREE THAT ANY AND ALL MATTERS SHALL BE DECIDED BY A JUDGE OR ARBITRATOR WITHOUT A JURY TO THE FULLEST EXTENT PERMISSIBLE UNDER APPLICABLE LAW."
            + "8.	In this Agreement, the term \"proprietary information\" means any information disclosed for the evaluation or discussion contemplated hereby, that the disclosing party owns or otherwise controls, except information which:"
            + "a.	is already known to, or independently developed by, the receiving party;"
            + "b.	is already publicly available or becomes publicly available without a breach of this Agreement by the receiving party;"
            + "c.	is lawfully received by the receiving party from a third party;"
            + "d.	is not either (i) disclosed in writing and identified thereon as confidential or proprietary, or (ii) if first disclosed orally, identified as confidential or proprietary at the time of oral disclosure and so confirmed in writing within thirty (30) days after such oral disclosure, except that information disclosed under this Agreement which would reasonably be expected, by the receiving party, to be considered confidential in the context in which such information is disclosed shall be considered proprietary information; "
            + "e.	becomes known to the receiving party by examining a product or merchandise made publicly available by the disclosing party; or"
            + "f.	is required to be disclosed by law or a valid order by a court or other governmental body, provided that the receiving party provides the disclosing party with prior written notice of such disclosure in order to permit the disclosing party to seek confidential treatment of such information."
            + "Notwithstanding the foregoing, the receiving party shall treat as proprietary information under this Agreement, any information which would reasonably be expected, by the receiving party, to be considered confidential in the context in which such information is disclosed. "
            + "9.	Upon written request of the disclosing party, the receiving party shall promptly return to the disclosing party all the proprietary information disclosed by the disclosing party.  Upon termination of this Agreement, unless requested otherwise in writing by the disclosing party, the receiving party shall return to the disclosing party or destroy and provide the disclosing party with notice of such destruction, all proprietary information disclosed by the disclosing party including all copies."
            + "10.	This Agreement contains the final, complete and exclusive agreement of the parties relative to the subject matter hereof, and supersedes all prior and contemporaneous understanding and agreements relating thereto."
            + "The parties hereto, intending to be legally bound hereby, have caused this Agreement to be duly executed as of the later date written below."
            + "Contractforce %s"
            + "                                                                                (%s)"
            + "Signed:						Signed:				"
            + "By:								By:					"
            + "Title:							Title:					"
            + "Date:							Date:					";
}

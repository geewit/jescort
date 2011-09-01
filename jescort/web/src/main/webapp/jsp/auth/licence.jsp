<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="content">
    <form method="post" action="/forum/">
        <p class="message">In order to proceed, you must agree to the following:</p>
        <br>
        <h2 class="maintitle">Registration Terms &amp; Rules</h2>
        <div class="general_box rounded-bot">
            <h3>Forum Terms &amp; Rules</h3>
            <p>
                <b>Forum Terms &amp; Rules</b><br>
                <br>
                Please take a moment to review these rules detailed below. If you agree
                with them and wish to proceed with the registration, simply click the
                "Register" button below. To cancel this registration, simply hit the
                'back' button on your browser.<br>
                <br>
                Please remember that we are not responsible for any messages posted. We
                do not vouch for or warrant the accuracy, completeness or usefulness of
                any message, and are not responsible for the contents of any message.The
                 messages express the views of the author of the message, not
                necessarily the views of this bulletin board. Any user who feels that a
                posted message is objectionable is encouraged to contact us immediately
                by email. <br>
                <br>
                We have the ability to remove objectionable messages and we will make
                every effort to do so, within a reasonable time frame, if we determine
                that removal is necessary.<br>
                <br>
                You agree, through your use of this service, that you will not use this
                bulletin board to post any material which is knowingly false and/or
                defamatory, inaccurate, abusive, vulgar, hateful, harassing, obscene,
                profane, sexually oriented, threatening, invasive of a person's privacy,
                or violation of any law. You agree not to post any copyrighted material
                unless the copyright is owned by you or by this bulletin board.
                Additionally you also agree that <a href="/forum/topic/632944-does-neowin-delete-member-accounts/">we do not delete your member account or contribution</a> should you choose to leave.
            </p>
            <fieldset style="margin: 10px -10px -10px -10px;" class="submit rounded-bot">
                <input type="checkbox" value="1" name="agree_to_terms" id="agree_cbox" class="input_check">
                <label for="agree_cbox"><strong>I have read, understood and agree to these rules and conditions</strong></label>
                <br><br>
                <input type="submit" class="input_submit" value="Continue Registration">
            </fieldset>
        </div>
    </form>
    <!-- // Content Wrapper -->
    <div class="clear"></div>
</div>
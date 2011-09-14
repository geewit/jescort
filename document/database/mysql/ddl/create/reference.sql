
ALTER TABLE attachment_datas ADD CONSTRAINT `attachment_datas.attachment_fk` FOREIGN KEY (`id`) REFERENCES `attachments` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE attachment_properties ADD CONSTRAINT `attachment_properties.attachment_fk` FOREIGN KEY (`id`) REFERENCES `attachments` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE category_moderator_map ADD CONSTRAINT `category_moderator_map.category_fk` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE category_moderator_map ADD CONSTRAINT `category_moderator_map.moderator_fk` FOREIGN KEY (`moderator_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE category_properties ADD CONSTRAINT `category_properties.category_fk` FOREIGN KEY (`id`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE drafts ADD CONSTRAINT `drafts.user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE draft_properties ADD CONSTRAINT `draft_properties.draft_fk` FOREIGN KEY (`id`) REFERENCES `drafts` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE forum_moderator_map ADD CONSTRAINT `forum_moderator_map.forum_fk` FOREIGN KEY (`forum_id`) REFERENCES `forums` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE forum_moderator_map ADD CONSTRAINT `forum_moderator_map.moderator_fk` FOREIGN KEY (`moderator_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE message_attachment_map ADD CONSTRAINT `message_attachment_map.message_fk` FOREIGN KEY (`message_id`) REFERENCES `messages` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE message_attachment_map ADD CONSTRAINT `message_attachment_map.attachment_fk` FOREIGN KEY (`attachment_id`) REFERENCES `attachments` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE message_properties ADD CONSTRAINT `message_properties.message_fk` FOREIGN KEY (`id`) REFERENCES `messages` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE message_recipient_map ADD CONSTRAINT `message_recipient_map.message_fk` FOREIGN KEY (`message_id`) REFERENCES `messages` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE message_recipient_map ADD CONSTRAINT `message_recipient_map.recipient_fk` FOREIGN KEY (`recipient_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE post_attachment_map ADD CONSTRAINT `post_attachment_map.post_fk` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE post_attachment_map ADD CONSTRAINT `post_attachment_map.attachment_fk` FOREIGN KEY (`attachment_id`) REFERENCES `attachments` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE post_edits ADD CONSTRAINT `post_edits.post_fk` FOREIGN KEY (`id`) REFERENCES `posts` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE post_edits ADD CONSTRAINT `post_edits.editor_fk` FOREIGN KEY (`editor_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE post_properties ADD CONSTRAINT `post_properties.post_fk` FOREIGN KEY (`id`) REFERENCES `posts` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE topic_properties ADD CONSTRAINT `topic_properties.post_fk` FOREIGN KEY(`id`) references `topics`(`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE addresses ADD CONSTRAINT `addresses.user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE addresses ADD CONSTRAINT `addresses.location_fk` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE emails ADD CONSTRAINT `emails.user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE groups ADD CONSTRAINT `groups.parent_fk` FOREIGN KEY (`parent_id`) REFERENCES `groups` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION;
ALTER TABLE group_role_map ADD CONSTRAINT `group_role_map.group_fk` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE group_role_map ADD CONSTRAINT `group_role_map.role_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE locations ADD CONSTRAINT `locations.parent_fk` FOREIGN KEY (`parent_id`) REFERENCES `locations` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION;
ALTER TABLE permissions ADD CONSTRAINT `permissions.role_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE user_group_map ADD CONSTRAINT `user_group_map.user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE user_group_map ADD CONSTRAINT `user_group_map.group_fk` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE user_profiles ADD CONSTRAINT `user_profiles.user_fk` FOREIGN KEY (`id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
ALTER TABLE user_properties ADD CONSTRAINT `user_properties.user_fk` FOREIGN KEY (`id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;


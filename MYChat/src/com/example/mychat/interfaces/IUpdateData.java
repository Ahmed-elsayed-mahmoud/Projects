package com.example.mychat.interfaces;
import com.example.mychat.types.FriendInfo;
import com.example.mychat.types.MessageInfo;


public interface IUpdateData {
	public void updateData(MessageInfo[] messages, FriendInfo[] friends, FriendInfo[] unApprovedFriends, String userKey);

}
